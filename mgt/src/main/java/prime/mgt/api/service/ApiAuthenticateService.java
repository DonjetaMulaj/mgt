package prime.mgt.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.domain.User;
import prime.mgt.service.impl.UserService;

/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 *
 */
@Component
public class ApiAuthenticateService extends ApiService{
	@Autowired
	private UserService userService;
	
	@Override
	public ApiServiceVO doAction(RequestHolder requestHolder, HttpServletRequest request) throws ApiException {
		ApiServiceVO asvo = new ApiServiceVO();
		String userName = requestHolder.getUserName();
		String email = requestHolder.getEmail();
		String passwrod = requestHolder.getPassword();
		User user = userService.getUser(userName, email, passwrod);
		if (user == null) {
			throw new ApiException(ApiErrorCode.ERR0003);
		}
		asvo.setSuccessResponseParams();		
		return asvo;
	}
}