package prime.mgt.api.util.validation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import prime.mgt.api.enums.AbstractAction;
import prime.mgt.api.util.validation.ApiReqValidationRules.Action.Params.Param;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "action" })
@XmlRootElement(name = "actions")
public class ApiReqValidationRules {
	protected List<ApiReqValidationRules.Action> action;

	public List<ApiReqValidationRules.Action> getAction() {
		if (action == null) {
			action = new ArrayList<ApiReqValidationRules.Action>();
		}
		return this.action;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "params" })
	public static class Action {
		@XmlElement(required = true)
		private ApiReqValidationRules.Action.Params params;
		@XmlAttribute
		private String name;

		public ApiReqValidationRules.Action.Params getParams() {
			return params;
		}

		public void setParams(ApiReqValidationRules.Action.Params value) {
			this.params = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String value) {
			this.name = value;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "param" })
		public static class Params {
			protected List<ApiReqValidationRules.Action.Params.Param> param;

			public List<ApiReqValidationRules.Action.Params.Param> getParam() {
				if (param == null) {
					param = new ArrayList<ApiReqValidationRules.Action.Params.Param>();
				}
				return this.param;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class Param {
				@XmlValue
				protected String value;
				@XmlAttribute(required = true)
				protected String name;
				@XmlAttribute(required = true)
				protected String required;
				@XmlAttribute(required = true)
				protected BigInteger maxLength;
				@XmlAttribute(required = true)
				protected String type;
				@XmlAttribute
				protected String format;
				@XmlAttribute
				protected String defaultValue;
				@XmlAttribute
				protected String enumClass;
				@XmlAttribute
				protected String ifParamNotPresent;
				@XmlAttribute
				protected String ifParamValue;

				public String getDefaultValue() {
					return defaultValue;
				}

				public void setDefaultValue(String defaultValue) {
					this.defaultValue = defaultValue;
				}

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public String getName() {
					return name;
				}

				public void setName(String value) {
					this.name = value;
				}

				public String getRequired() {
					return required;
				}

				public void setRequired(String value) {
					this.required = value;
				}

				public BigInteger getMaxLength() {
					return maxLength;
				}

				public void setMaxLength(BigInteger value) {
					this.maxLength = value;
				}

				public String getType() {
					return type;
				}

				public void setType(String value) {
					this.type = value;
				}

				public String getFormat() {
					return format;
				}

				public void setFormat(String value) {
					this.format = value;
				}

				public String getEnumClass() {
					return enumClass;
				}

				public void setEnumClass(String enumClass) {
					this.enumClass = enumClass;
				}

				public String getIfParamNotPresent() {
					return ifParamNotPresent;
				}

				public void setIfParamNotPresent(String ifParamNotPresent) {
					this.ifParamNotPresent = ifParamNotPresent;
				}

				public String getIfParamValue() {
					return ifParamValue;
				}

				public void setIfParamValue(String ifParamValue) {
					this.ifParamValue = ifParamValue;
				}
			}
		}
	}

	public List<Param> getParamsForAction(AbstractAction actionType) {
		List<Param> result = new ArrayList<ApiReqValidationRules.Action.Params.Param>();
		for (Action action : this.getAction()) {
			if (actionType.name().equalsIgnoreCase(action.getName())) {
				for (Param param : action.getParams().getParam()) {
					result.add(param);
				}
			}
		}
		return result;
	}
}