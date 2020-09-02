package prime.mgt.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiAction;
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

	public void login(RequestHolder requestHolder, HttpServletRequest req) throws ApiException {
		String userName = req.getParameter(requestHolder.getUserName());
		String userEmail = req.getParameter(requestHolder.getEmail());
		String userPassword = req.getParameter(requestHolder.getPassword());
		String action = req.getParameter(requestHolder.getAction());
		ApiAction apiAction = ApiAction.fromString(action);
		User user = userService.getUser(userName, userEmail, userPassword);
		if (user == null) {
			throw new ApiException(ApiErrorCode.ERR0003);
		}
		apiRoleChecker.checkUserRole(apiAction, user);
	}
}
