package prime.mgt.api.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiAction;
import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.exception.ApiException;
import prime.mgt.domain.ActionUserRoleMapping;
import prime.mgt.domain.User;
import prime.mgt.domain.enums.UserRole;

/**
 * @author Donjeta Mulaj
 */
@Component
public class ApiRoleChecker {
	@Autowired
	private ActionUserRoleMapping actionUserRoleMapping;

	/**
	 * Checks whether the specified role has permission for the specified
	 * action/subaction
	 * 
	 * @param role
	 * @param action
	 * @return
	 */
	public void checkUserRole(ApiAction action, User user) throws ApiException {
		boolean hasRole = false;
		@SuppressWarnings("static-access")
		Map<ApiAction, List<UserRole>> list = actionUserRoleMapping.getActionUserRoleMapping();
		Set<UserRole> roles = user.getUserRoles();
		if (action != null) {
			hasRole = hasRole(roles, action);
		}
		if (!hasRole) {
			throw new ApiException(ApiErrorCode.ERR0002, "User does not have role access " + list.get(action) + " to perform this action");
		}
	}

	public boolean hasRole(Set<UserRole> userRoles, ApiAction action) {
		List<UserRole> roles;
		@SuppressWarnings("static-access")
		Map<ApiAction, List<UserRole>> list = actionUserRoleMapping.getActionUserRoleMapping();
		if (action != null) {
			roles = list.get(action);
		} else {
			throw new IllegalArgumentException("Invalid Action specified as argument");
		}
		if (roles == null || roles.isEmpty()) {
			throw new IllegalStateException("Roles not found on actionToRoleMapper! Check configuration file");
		}
		for (UserRole ur : userRoles) {
			if (roles.contains(ur)) {
				return true;
			}
		}
		return false;
	}
}
