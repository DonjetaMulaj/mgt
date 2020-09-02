package prime.mgt.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiAction;
import prime.mgt.domain.enums.UserRole;

@Component
public class ActionUserRoleMapping {
	
	public static Map<ApiAction, List<UserRole>> actionUserRoleMapping;

	public static Map<ApiAction, List<UserRole>> getActionUserRoleMapping() {
		actionUserRoleMapping = new HashMap<ApiAction, List<UserRole>>();
		// User roles that can do api authenticate action 
		actionUserRoleMapping.put(ApiAction.APIAUTHENTICATE, Arrays.asList(UserRole.BASIC, UserRole.BASIC, UserRole.ANALYST));
		// User roles that can do api query project 
		actionUserRoleMapping.put(ApiAction.APIQUERYPROJECT, Arrays.asList(UserRole.ANALYST));		
		return actionUserRoleMapping;
	}
	
	
}
