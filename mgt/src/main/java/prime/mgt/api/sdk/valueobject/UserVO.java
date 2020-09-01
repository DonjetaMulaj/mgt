package prime.mgt.api.sdk.valueobject;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import prime.mgt.domain.enums.UserRole;

@JsonInclude(Include.NON_NULL)
public class UserVO {
	private String userName;
	private String userPassword;
	private Set<UserRole> userRoles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public static class Builder {
		private String userName;
		private String userPassword;
		private Set<UserRole> userRoles;

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder userPassword(String userPassword) {
			this.userPassword = userPassword;
			return this;
		}

		public Builder userRoles(Set<UserRole> userRoles) {
			this.userRoles = userRoles;
			return this;
		}

		public UserVO build() {
			return new UserVO(this);
		}
	}

	private UserVO(Builder builder) {
		this.userName = builder.userName;
		this.userPassword = builder.userPassword;
		this.userRoles = builder.userRoles;
	}
}
