package prime.mgt.api.service;

import org.springframework.stereotype.Component;

import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;

/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 *
 */
@Component
public class ApiAuthenticateService extends ApiService{

	@Override
	public ApiServiceVO doAction(RequestHolder requestHolder) throws ApiException {
		ApiServiceVO asvo = new ApiServiceVO();
		String user = requestHolder.getUserName();
		String email = requestHolder.getEmail();
		String passwrod = requestHolder.getPassword();				
		asvo.setSuccessResponseParams();
		
		return asvo;
	}
}