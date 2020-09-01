package prime.mgt.enums;

/**
 * @author Donjeta Mulaj<donjeta.mulaj@gmail.com>
 */
public enum Permission {
	API_AUTHENTICATE(new Role[] { Role.ADMIN }), 
	API_QUERYPROJECT(new Role[] { Role.ADMIN }), 
	API_SEARCHPROJECT(new Role[] { Role.ADMIN });
	private Role[] defaultRoles;

	public Role[] getDefaultRoles() {
		return defaultRoles;
	}

	public void setDefaultRoles(Role[] defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	private Permission(Role[] defaultRoles) {
		this.defaultRoles = defaultRoles;
	}
}
