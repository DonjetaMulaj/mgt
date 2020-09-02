package prime.mgt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.domain.User;
import prime.mgt.service.impl.UserService;

/**
 * @author Donjeta Mulaj
 */
@Component
public class ApiLoginService {
	@Autowired
	private ApiRoleChecker apiRoleChecker;
	@Autowired
	private UserService userService;

	public void login(RequestHolder requestHolder) throws ApiException {
		String userName = requestHolder.getUserName();
		String userEmail = requestHolder.getEmail();
		String userPassword = requestHolder.getPassword();
		User user = userService.getUser(userName, userEmail, userPassword);
		if (user == null) {
			throw new ApiException(ApiErrorCode.ERR0003);
		}
		apiRoleChecker.checkUserRole(requestHolder.getAction(), user);
	}
}
