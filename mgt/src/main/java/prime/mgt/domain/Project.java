package prime.mgt.domain;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Project extends PO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1052362240978678540L;
	private String projectName;
	private String projectDescription;
	private Date projectCreatedTs;
	private String parentProjectId;
	private User user;
	private Set<ProjectItems> projectItems;
	private Set<Project> childProject;

	public Project() {
		super();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getProjectCreatedTs() {
		return projectCreatedTs;
	}

	public void setProjectCreatedTs(Date projectCreatedTs) {
		this.projectCreatedTs = projectCreatedTs;
	}

	public String getParentProjectId() {
		return parentProjectId;
	}

	public void setParentProjectId(String parentProjectId) {
		this.parentProjectId = parentProjectId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ProjectItems> getProjectItems() {
		return projectItems;
	}

	public void setProjectItems(Set<ProjectItems> projectItems) {
		this.projectItems = projectItems;
	}

	public Set<Project> getChildProject() {
		return childProject;
	}

	public void setChildProject(Set<Project> childProject) {
		this.childProject = childProject;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(projectName).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Project) {
			final Project other = (Project) obj;
			return new EqualsBuilder().append(id, other.id).append(projectName, other.projectName).isEquals();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", projectDescription=" + projectDescription + ", projectCreatedTs=" + projectCreatedTs
				+ ", parentProjectId=" + parentProjectId + ", user=" + user + ", projectItems=" + projectItems + ", childProject=" + childProject + "]";
	}

	public static class Builder {
		private String id;
		private String projectName;
		private String projectDescription;
		private Date projectCreatedTs;
		private String parentProjectId;
		private User user;
		private Set<ProjectItems> projectItems;
		private Set<Project> childProject;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder projectName(String projectName) {
			this.projectName = projectName;
			return this;
		}

		public Builder projectDescription(String projectDescription) {
			this.projectDescription = projectDescription;
			return this;
		}

		public Builder projectCreatedTs(Date projectCreatedTs) {
			this.projectCreatedTs = projectCreatedTs;
			return this;
		}

		public Builder parentProjectId(String parentProjectId) {
			this.parentProjectId = parentProjectId;
			return this;
		}

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public Builder projectItems(Set<ProjectItems> projectItems) {
			this.projectItems = projectItems;
			return this;
		}

		public Builder childProject(Set<Project> childProject) {
			this.childProject = childProject;
			return this;
		}

		public Project build() {
			return new Project(this);
		}
	}

	private Project(Builder builder) {
		this.id = builder.id;
		this.projectName = builder.projectName;
		this.projectDescription = builder.projectDescription;
		this.projectCreatedTs = builder.projectCreatedTs;
		this.parentProjectId = builder.parentProjectId;
		this.user = builder.user;
		this.projectItems = builder.projectItems;
		this.childProject = builder.childProject;
	}
}
