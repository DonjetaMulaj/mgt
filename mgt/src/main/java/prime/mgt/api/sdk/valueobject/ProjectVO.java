package prime.mgt.api.sdk.valueobject;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProjectVO {
	private String projectName;
	private String projectDescription;
	private Date projectCreatedTs;
	private String parentProjectId;
	private UserVO user;
	private Set<ProjectItemsVO> projectItems;
	private Set<ProjectVO> childProject;

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

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Set<ProjectItemsVO> getProjectItems() {
		return projectItems;
	}

	public void setProjectItems(Set<ProjectItemsVO> projectItems) {
		this.projectItems = projectItems;
	}

	public Set<ProjectVO> getChildProject() {
		return childProject;
	}

	public void setChildProject(Set<ProjectVO> childProject) {
		this.childProject = childProject;
	}
	
	
	public static class Builder {
		private String projectName;
		private String projectDescription;
		private Date projectCreatedTs;
		private String parentProjectId;
		private UserVO user;
		private Set<ProjectItemsVO> projectItems;
		private Set<ProjectVO> childProject;

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

		public Builder user(UserVO user) {
			this.user = user;
			return this;
		}

		public Builder projectItems(Set<ProjectItemsVO> projectItems) {
			this.projectItems = projectItems;
			return this;
		}

		public Builder childProject(Set<ProjectVO> childProject) {
			this.childProject = childProject;
			return this;
		}
		public ProjectVO build() {
			return new ProjectVO(this);
		}
	}
	
	private ProjectVO(Builder builder) {
		this.projectName = builder.projectName;
		this.projectDescription = builder.projectDescription;
		this.projectCreatedTs = builder.projectCreatedTs;
		this.parentProjectId = builder.parentProjectId;
		this.user = builder.user;
		this.projectItems = builder.projectItems;
		this.childProject = builder.childProject;
	}
}
