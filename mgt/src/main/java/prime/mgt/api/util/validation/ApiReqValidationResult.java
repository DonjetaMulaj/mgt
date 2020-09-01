package prime.mgt.api.util.validation;

import java.util.Map;

import prime.mgt.api.enums.ApiErrorCode;

/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@asseco-see.com>
 *
 */
public class ApiReqValidationResult {
	private boolean successful;
	private ApiErrorCode errorCode;
	private String paramViolator;
	private Map<String, String> validatedParams;

	public Map<String, String> getValidatedParams() {
		return this.validatedParams;
	}

	public ApiReqValidationResult(boolean successful, ApiErrorCode errorCode, String paramViolator, Map<String, String> validatedParams) {
		this.successful = successful;
		this.errorCode = errorCode;
		this.paramViolator = paramViolator;
		this.validatedParams = validatedParams;
	}

	public boolean isSuccessful() {
		return this.successful;
	}

	public ApiErrorCode getErrorCode() {
		return this.errorCode;
	}

	public String getParamViolator() {
		return this.paramViolator;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public void setErrorCode(ApiErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public void setParamViolator(String paramViolator) {
		this.paramViolator = paramViolator;
	}
}
