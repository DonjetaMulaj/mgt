package prime.mgt.api.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.AbstractAction;
import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.util.validation.ApiReqValidationResult;
import prime.mgt.api.util.validation.ApiRequestValidator;

/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 *
 */
@Component
public class ApiValidationService {
	@Autowired
	private ApiRequestValidator apiRequestReader;
	@Autowired
	private ApiUtil apiUtil;
	private static final Logger logger = LogManager.getLogger(ApiValidationService.class);
	
	public void validateRequest(RequestHolder requestHolder, HttpServletRequest req, AbstractAction action) throws ApiException {
		ApiReqValidationResult result = apiRequestReader.validateParams(req, action);
		apiRequestReader.doAdditionalValidation(result);
		requestHolder.setValidatedParams(result.getValidatedParams());
		if (!result.isSuccessful()) {
			logger.debug("Api request validation fail! ApiErrorCode:" + result.getErrorCode() + ", Violator: " + result.getParamViolator());
			throw new ApiException(result.getErrorCode(), result.getParamViolator());
		}
	}
	
	
}
