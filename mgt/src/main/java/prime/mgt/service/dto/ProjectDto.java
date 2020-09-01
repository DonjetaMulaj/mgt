package prime.mgt.service.dto;

import prime.mgt.domain.enums.ProjectType;

public class ProjectDto {
	private ProjectType projectType;

	public ProjectDto() {
		super();
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public static class Builder {
		private ProjectType projectType;

		public Builder projectType(ProjectType projectType) {
			this.projectType = projectType;
			return this;
		}

		public ProjectDto build() {
			return new ProjectDto(this);
		}
	}

	private ProjectDto(Builder builder) {
		this.projectType = builder.projectType;
	}
}
