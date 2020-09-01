package prime.mgt.service.dto;

import prime.mgt.domain.enums.ProjectType;

public class ProjectDto {
	private ProjectType projectType;
	private MarketingDto marketing;
	private RetailDto reatil;

	public ProjectDto() {
		super();
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public MarketingDto getMarketing() {
		return marketing;
	}

	public void setMarketing(MarketingDto marketing) {
		this.marketing = marketing;
	}

	public RetailDto getReatil() {
		return reatil;
	}

	public void setReatil(RetailDto reatil) {
		this.reatil = reatil;
	}

	public static class Builder {
		private ProjectType projectType;
		private MarketingDto marketing;
		private RetailDto reatil;

		public Builder projectType(ProjectType projectType) {
			this.projectType = projectType;
			return this;
		}

		public Builder marketing(MarketingDto marketing) {
			this.marketing = marketing;
			return this;
		}

		public Builder reatil(RetailDto reatil) {
			this.reatil = reatil;
			return this;
		}

		public ProjectDto build() {
			return new ProjectDto(this);
		}
	}

	private ProjectDto(Builder builder) {
		this.projectType = builder.projectType;
		this.marketing = builder.marketing;
		this.reatil = builder.reatil;
	}
}
