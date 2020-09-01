package prime.mgt.api.sdk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import prime.mgt.api.enums.ApiAction;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@JsonInclude(Include.NON_NULL)
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiServiceVO {
	private ApiAction action;
	private String errorMsg;
	private String errorCode;
	private String responseCode;
	private String responseMsg;
	private String violatorParam;

	public ApiAction getAction() {
		return action;
	}

	public void setAction(ApiAction action) {
		this.action = action;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getViolatorParam() {
		return violatorParam;
	}

	public void setViolatorParam(String violatorParam) {
		this.violatorParam = violatorParam;
	}

	public void setSuccessResponseParams() {
		this.responseCode = ResponseCode.APPROVEDCODE;
		this.responseMsg = ResponseMessage.APPROVEDMESSAGE;
	}

	public void setDeclinedResponseParams() {
		this.responseCode = ResponseCode.DECLINEDCODE;
		this.responseMsg = ResponseMessage.DECLINEDMESSAGE;
	}
}