package prime.mgt.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@Component
public class ApiSearchProjectService extends ApiService {
	@Override
	public ApiServiceVO doAction(RequestHolder requestHolder, HttpServletRequest request) throws ApiException {
		ApiServiceVO asvo = new ApiServiceVO();
		String filters = requestHolder.getFilters();
		
		asvo.setSuccessResponseParams();
		return asvo;
	}
}
