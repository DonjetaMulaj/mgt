package prime.mgt.service.dto;

import java.util.List;
import java.util.Map;

public class ProjectDto {
	Map<String, List<String>> filters;

	public ProjectDto() {
		super();
	}

	public static class Builder {
		public ProjectDto build() {
			return new ProjectDto(this);
		}
	}

	private ProjectDto(Builder builder) {
	}
}
