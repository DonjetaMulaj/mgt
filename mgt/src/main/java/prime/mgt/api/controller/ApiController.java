package prime.mgt.api.controller;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prime.mgt.api.enums.ApiAction;
import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.enums.ApiRequestParameter;
import prime.mgt.api.enums.Responseformat;
import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.api.service.ActionFactoryv2;
import prime.mgt.api.service.ApiLoginService;
import prime.mgt.api.service.ApiService;
import prime.mgt.api.util.ApiUtil;
import prime.mgt.api.util.ApiValidationService;
import prime.mgt.api.util.DateUtils;
import prime.mgt.api.util.ResponseConverter;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */

@Controller
public class ApiController {
	@Autowired
	private DateUtils dateUtils;
	@Autowired
	private ApiUtil apiUtil;
	@Autowired
	private ActionFactoryv2 actionFactory;
	@Autowired
	private ApiValidationService apiValidationService;
	@Autowired
	private ResponseConverter responseFormatter;
	@Autowired
	private ApiLoginService apiLoginService;
	private static final Logger logger = LogManager.getLogger(ApiController.class);

	@RequestMapping(value = { "api/service" }, method = RequestMethod.POST)
	public void apiv2(HttpServletRequest request, HttpServletResponse response) {
		String responseOutput = "";
		String responseCode = "";
		String responseErrorCode = "";
		ApiAction action = null;
		ApiServiceVO asvo = new ApiServiceVO();
		RequestHolder requestHolder = null;
		Responseformat responseFormat = Responseformat.JSON;
		requestHolder = new RequestHolder(request);
		try {
			apiUtil.validatePostRequest(request);
			action = getAction(request);
			ApiService apiService = actionFactory.getAction(action);
			apiValidationService.validateRequest(requestHolder, request, action);
			apiLoginService.login(requestHolder);
			asvo = apiService.doAction(requestHolder);
		} catch (ApiException e) {
			logger.trace("API Exception", e.getMessage());
			logger.debug("API v2 Fail: mid = {}, action = {}, errorCode: {}, violator: {}, errorMsg:{}", action, e.getMessage(), e.getApiErrorCode());
			e.printStackTrace();
		} finally {
			try {
				responseCode = asvo.getResponseCode();
				responseErrorCode = asvo.getErrorCode();
				responseOutput = responseFormatter.convert(asvo, responseFormat);
			} catch (Exception e) {
				logger.error("API v2 Internal Server Error", e);
				if (isEmpty(responseOutput)) {
					responseOutput = "INTERNAL_SERVER_ERROR";
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			try {
				response.getWriter().print(responseOutput);
			} catch (IOException e) {
				logger.error("Exception throw while closing PrintWriter", e);
			}
		}
	}

	private ApiAction getAction(HttpServletRequest req) throws ApiException {
		String action = req.getParameter(ApiRequestParameter.ACTION.name());
		try {
			return ApiAction.valueOf(action);
		} catch (Exception e) {
			throw new ApiException(ApiErrorCode.ERR0001, ApiRequestParameter.ACTION.name());
		}
	}
}
