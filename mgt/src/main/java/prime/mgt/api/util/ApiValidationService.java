package prime.mgt.api.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.AbstractAction;
import prime.mgt.api.enums.ApiAction;
import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.enums.ApiRequestParameter;
import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.util.validation.ApiRequestValidator;
import prime.mgt.domain.ApiReqParamDefinitation;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@Component
public class ApiValidationService {
	@Autowired
	private ApiRequestValidator apiRequestReader;
	@Autowired
	private ApiReqParamDefinitation apiReqParamDefinitation;
	private static final Logger logger = LogManager.getLogger(ApiValidationService.class);

	/*
	 * public void validateRequest(RequestHolder requestHolder, HttpServletRequest req, AbstractAction action) throws ApiException {
	 * ApiReqValidationResult result = apiRequestReader.validateParams(req, action);
	 * apiRequestReader.doAdditionalValidation(result);
	 * requestHolder.setValidatedParams(result.getValidatedParams());
	 * if (!result.isSuccessful()) {
	 * logger.debug("Api request validation fail! ApiErrorCode:" + result.getErrorCode() + ", Violator: " + result.getParamViolator());
	 * throw new ApiException(result.getErrorCode(), result.getParamViolator());
	 * }
	 * }
	 */
	public void validateRequest(RequestHolder requestHolder, HttpServletRequest req, AbstractAction action) throws ApiException {
		@SuppressWarnings("static-access")
		Map<ApiAction, List<ApiRequestParameter>> apiActionParams = apiReqParamDefinitation.getApiReqParamsDefinitation();
		List<ApiRequestParameter> validParamsForAction = apiActionParams.get(action);
		for (ApiRequestParameter param : validParamsForAction) {
			String isParamInReq = req.getParameter(param.name());
			if (StringUtils.isBlank(isParamInReq)) {
				logger.debug("Missing required parameter"+ param + "for this Api action "+ action.name());
				throw new ApiException(ApiErrorCode.ERR0001, param.name());
			}
		}
	}
}
