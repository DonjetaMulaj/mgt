package prime.mgt.api.service;

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
	public ApiServiceVO doAction(RequestHolder requestHolder) throws ApiException {
		ApiServiceVO asvo = new ApiServiceVO();
		asvo.setSuccessResponseParams();
		return asvo;
	}
}
