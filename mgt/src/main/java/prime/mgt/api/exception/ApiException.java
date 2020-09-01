package prime.mgt.api.exception;

import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.api.sdk.ResponseCode;
import prime.mgt.api.sdk.ResponseMessage;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
public class ApiException extends Exception {
	private ApiErrorCode apiErrorCode;
	private String errorCauser;

	private static final long serialVersionUID = -6837240447748469747L;
	
	public ApiException(ApiErrorCode apiErrorCode, String errorCauser) {
		this.apiErrorCode = apiErrorCode;
		this.errorCauser = errorCauser;
	}

	/**
	 * Used when we do not want to specify what request parameter caused the error that occurred.
	 * 
	 * @param apiErrorCode
	 */
	public ApiException(ApiErrorCode apiErrorCode) {
		this.apiErrorCode = apiErrorCode;
		this.errorCauser = "";
	}

	public String getErrorCauser() {
		return errorCauser;
	}

	public void setErrorCauser(String errorCauser) {
		this.errorCauser = errorCauser;
	}

	public ApiErrorCode getApiErrorCode() {
		return apiErrorCode;
	}

	public void addApiErrorParams(ApiServiceVO asvo) {
		asvo.setResponseMsg(ResponseMessage.DECLINEDMESSAGE);
		asvo.setResponseCode(ResponseCode.DECLINEDCODE);
		asvo.setErrorCode("");
		asvo.setErrorMsg("");
		asvo.setViolatorParam("");
	}
}
