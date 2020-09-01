package prime.mgt.domain;

import java.util.Date;
import java.util.Set;

import prime.mgt.domain.enums.ProjectType;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
public class Project {
	private ProjectType projectType;
	private ProjectMarketing marketing;
	private ProjectRetail retail;
	private String projectName;
	private String projectDescription;
	private Date projectCreatedTs;
	private User user;
	private Set<ProjectItems> projectItems;

	public Project() {
		super();
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public ProjectMarketing getMarketing() {
		return marketing;
	}

	public void setMarketing(ProjectMarketing marketing) {
		this.marketing = marketing;
	}

	public ProjectRetail getRetail() {
		return retail;
	}

	public void setRetail(ProjectRetail retail) {
		this.retail = retail;
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


	public static class Builder {
		private ProjectType projectType;
		private ProjectMarketing marketing;
		private ProjectRetail retail;
		private String projectName;
		private String projectDescription;
		private Date projectCreatedTs;
		private User user;

		public Builder projectType(ProjectType projectType) {
			this.projectType = projectType;
			return this;
		}

		public Builder marketing(ProjectMarketing marketing) {
			this.marketing = marketing;
			return this;
		}

		public Builder retail(ProjectRetail retail) {
			this.retail = retail;
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
		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public Project build() {
			return new Project(this);
		}
	}

	private Project(Builder builder) {
		this.projectType = builder.projectType;
		this.marketing = builder.marketing;
		this.retail = builder.retail;
		this.projectName = builder.projectName;
		this.projectDescription = builder.projectDescription;
		this.projectCreatedTs = builder.projectCreatedTs;
		this.user = builder.user;
	}
}
