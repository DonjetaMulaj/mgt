package prime.mgt.domain;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import prime.mgt.domain.enums.UserRole;

public class User extends PO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 488920729970325879L;
	private String userName;
	private String userEmail;
	private String userPassword;
	private Date userCreatedTs;
	private Set<UserRole> userRoles;

	public User() {
		super();
	}

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

	public Date getUserCreatedTs() {
		return userCreatedTs;
	}

	public void setUserCreatedTs(Date userCreatedTs) {
		this.userCreatedTs = userCreatedTs;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userName).append(userEmail).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof User) {
			final User other = (User) obj;
			return new EqualsBuilder().append(userName, other.userName).append(userEmail, other.userEmail).isEquals();
		} else {
			return false;
		}
	}

	public static class Builder {
		private String userName;
		private String userPassword;
		private Date userCreatedTs;
		private Set<UserRole> userRoles;

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder userPassword(String userPassword) {
			this.userPassword = userPassword;
			return this;
		}

		public Builder userCreatedTs(Date userCreatedTs) {
			this.userCreatedTs = userCreatedTs;
			return this;
		}

		public Builder userRoles(Set<UserRole> userRoles) {
			this.userRoles = userRoles;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	private User(Builder builder) {
		this.userName = builder.userName;
		this.userPassword = builder.userPassword;
		this.userCreatedTs = builder.userCreatedTs;
		this.userRoles = builder.userRoles;
	}
}
