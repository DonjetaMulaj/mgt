package prime.mgt.api.util.validation;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.AbstractAction;
import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.enums.ApiRequestParameter;
import prime.mgt.api.util.JaxbFactory;
import prime.mgt.api.util.validation.ApiReqValidationRules.Action.Params.Param;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@Component
public class ApiRequestValidator {
	@Autowired
	private JaxbFactory jaxbFactory;
	private StreamSource source;
	private Map<String, Set<String>> enumContainer;
	private ApiReqValidationRules validationRules;
	private static final String ENUM = "enum";
	private static final String DECIMAL = "decimal";
	private static final String INTEGER = "integer";
	private static final String DATE = "date";
	private static final String ENUM_PACKAGE = "goprime.fictive.api.enums";
	private static final String STRING = "string";
	private static final String API_REQUEST_PARAM_DEFINITIONS_XML = "api-request-param-definitions.xml";
	private static final Logger logger = LogManager.getLogger(ApiRequestValidator.class);

	public ApiReqValidationRules getValidationRules() {
		return validationRules;
	}

	@PostConstruct
	public void initializeRules() {
		if (this.getClass().getClassLoader() != null) {
			source = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(API_REQUEST_PARAM_DEFINITIONS_XML));
		}
		try {
			Unmarshaller unmarshaller = jaxbFactory.createUnmarshaller(ApiReqValidationRules.class);
			validationRules = (ApiReqValidationRules) unmarshaller.unmarshal(source);
		} catch (JAXBException e) {}
		Reflections reflections = new Reflections(ENUM_PACKAGE);
		enumContainer = new HashMap<>();
		addEnumDefinitions(reflections);
	}

	@SuppressWarnings({ "rawtypes" })
	private void addEnumDefinitions(Reflections reflections) {
		try {
			Set<Class<? extends Enum>> allClasses = reflections.getSubTypesOf(Enum.class);
			for (Class<? extends Enum> clazz : allClasses) {
				Class<?> c = Class.forName(clazz.getName());
				List enumConstans = Arrays.asList(c.getEnumConstants());
				Set<String> stringValues = new HashSet<>();
				for (int i = 0; i < enumConstans.size(); i++) {
					Enum e = (Enum) enumConstans.get(i);
					stringValues.add(e.name().toUpperCase());
				}
				enumContainer.put(c.getName(), stringValues);
			}
		} catch (ClassNotFoundException x) {
			// "Could not find enums in package: " + ENUM_PACKAGE
			logger.error("Could not find enums in package: " + ENUM_PACKAGE, x);
		}
	}
	
	public ApiReqValidationResult validateParams(HttpServletRequest req, AbstractAction actionType) {
		List<Param> params = validationRules.getParamsForAction(actionType);
		if (params.isEmpty()) {
			throw new IllegalStateException("No params defined for action " + actionType + " in " + API_REQUEST_PARAM_DEFINITIONS_XML);
		}
		Map<String, String> validatedParams = new HashMap<>();
		boolean validationSucceeded = true;
		ApiErrorCode firstErrorCode = null;
		String firstParamViolator = null;
		for (Param param : params) {
			String name = param.name.toUpperCase();
			String required = param.required;
			Boolean isRequired = required.equals("required");
			Boolean isConditional = required.equals("conditional");
			BigInteger maxLength = param.maxLength;
			String type = param.type;
			String format = param.format;
			String enumClass = param.enumClass;
			String value = req.getParameter(name);
			String ifParamNotPresent = param.ifParamNotPresent;
			String ifParamValue = param.ifParamValue;
			boolean paramOk = true;
			if (isEmpty(value) && isRequired) {
				paramOk = false;
				firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
				firstParamViolator = rememberParamViolator(firstParamViolator, name);
			} else if (isEmpty(value) && isConditional) {
				if (isNotEmpty(ifParamValue)) {
					IllegalStateException e = new IllegalStateException(
							"\"ifParamValue\" not properly defined for " + param.name + ", action: " + actionType + " in " + API_REQUEST_PARAM_DEFINITIONS_XML);
					String keyValue[] = ifParamValue.split("=");
					String paramName = keyValue[0].trim();
					if (keyValue.length > 1) {
						String[] values = keyValue[1].trim().split("\\|");
						List<String> expectedValues = Arrays.asList(values);
						String actualValue = req.getParameter(paramName.toUpperCase());
						if (expectedValues.contains(actualValue)) {
							paramOk = false;
							firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
							firstParamViolator = rememberParamViolator(firstParamViolator, name);
						}
					} else {
						throw e;
					}
				} else if (isNotEmpty(ifParamNotPresent)) {
					if (!this.validateIfParamNotPresent(req, ifParamNotPresent)) {
						paramOk = false;
						firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
						//give priority to the one present in request as a key
						firstParamViolator = rememberParamViolator(firstParamViolator, getParamPresentInRequest(name, ifParamNotPresent.toUpperCase(), req));
					}
				} else {
					throw new IllegalStateException("Param conditional but ifParamNotPresent not set for param " + param.name + ", action: " + actionType
							+ " in " + API_REQUEST_PARAM_DEFINITIONS_XML);
				}
			} else if (isEmpty(value)) {
				//optional param
				if (param.defaultValue != null) {
					value = param.defaultValue;
				}
			}
			if (isNotBlank(value)) {
				value = value.trim();
				if (value.length() > maxLength.intValue()) {
					paramOk = false;
					firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
					firstParamViolator = rememberParamViolator(firstParamViolator, name);
				} else if (DATE.equals(type)) {
					if (!isValidDate(value, format)) {
						paramOk = false;
						firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
						firstParamViolator = rememberParamViolator(firstParamViolator, name);
					}
				} else if (INTEGER.equals(type)) {
					if (!isInteger(value)) {
						paramOk = false;
						firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
						firstParamViolator = rememberParamViolator(firstParamViolator, name);
					}
				} else if (DECIMAL.equals(type)) {
					if (!isDecimal(value)) {
						paramOk = false;
						firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
						firstParamViolator = rememberParamViolator(firstParamViolator, name);
					} else {
						validatedParams.put(name, value.replaceAll("\\,", "\\."));
						continue;
					}
				} else if (STRING.equals(type)) {
					if (isNotBlank(format)) {
						if (!value.matches(format)) {
							paramOk = false;
							firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
							firstParamViolator = rememberParamViolator(firstParamViolator, name);
						}
					}
				} else if (ENUM.equals(type)) {
					if (isEmpty(enumClass)) {
						throw new IllegalStateException("\"enumClass\" attribute not specified for param " + param.name + ", action: " + actionType + " in "
								+ API_REQUEST_PARAM_DEFINITIONS_XML);
					} else {
						Set<String> validEnumValues = enumContainer.get(enumClass);
						if (!validEnumValues.contains(value.toUpperCase())) {
							paramOk = false;
							firstErrorCode = rememberErrorCode(firstErrorCode, ApiErrorCode.ERR0001);
							firstParamViolator = rememberParamViolator(firstParamViolator, name);
						}
					}
				} else {
					throw new IllegalStateException("Unknown type of parameter specified for param " + param.name + ", action: " + actionType + " in "
							+ API_REQUEST_PARAM_DEFINITIONS_XML);
				}
			}
			if (paramOk) {
				validatedParams.put(name, value);
			} else {
				validationSucceeded = false;
			}
		}
		return new ApiReqValidationResult(validationSucceeded, firstErrorCode, firstParamViolator, validatedParams);
	}

	public boolean validateIfParamNotPresent(HttpServletRequest req, String ifParamNotPresent) {
		String[] splittedParams = ifParamNotPresent.split("\\|");
		for (String param : splittedParams) {
			if(isNotEmpty(req.getParameter(param.toUpperCase()))) {
				return true;
			}
		}
		return false;
		
	
	}
	
	public void doAdditionalValidation(ApiReqValidationResult result) {
		boolean firstValidationSucceeded = result.isSuccessful();
		if (!firstValidationSucceeded) {
			logger.debug("Errors found in params in first validation. Skipping additional validation.");
			return;
		}
	}
	private void setResultToUnsuccessful(ApiReqValidationResult result, ApiErrorCode errCode, ApiRequestParameter violatorParam) {
		result.setErrorCode(errCode);
		result.setParamViolator(violatorParam.name());
		result.setSuccessful(false);
	}


	private String getParamPresentInRequest(String param, String ifParamNotPresent, HttpServletRequest req) {
		for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
			if (entry.getKey().toUpperCase().equals(param)) {
				return param;
			}
			if (entry.getKey().toUpperCase().equals(ifParamNotPresent)) {
				return ifParamNotPresent;
			}
		}
		return param;
	}

	private ApiErrorCode rememberErrorCode(ApiErrorCode firstErrorCode, ApiErrorCode errorCode) {
		return (firstErrorCode == null) ? errorCode : firstErrorCode;
	}

	private String rememberParamViolator(String firstParamViolator, String paramViolator) {
		return (firstParamViolator == null) ? paramViolator : firstParamViolator;
	}

	private boolean isInteger(String value) {
		return StringUtils.isNumeric(value);
	}

	private boolean isDecimal(String paramValue) {
		if (paramValue.contains("\\.") && paramValue.contains("\\,")) {
			//not allowed to contain both . and ,
			return false;
		}
		String input = paramValue.replaceAll("\\,", "\\.");
		return NumberUtils.isNumber(input);
	}

	private boolean isValidDate(String value, String pattern) {
		try {
			new SimpleDateFormat(pattern).parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public Map<String, String> getActionParams(HttpServletRequest req, AbstractAction actionType) {
		Map<String, String> result = new HashMap<>();
		List<Param> params = validationRules.getParamsForAction(actionType);
		for (Param param : params) {
			String name = param.name;
			String paramValue = req.getParameter(name);
			result.put(name, paramValue);
		}
		return result;
	}
}
