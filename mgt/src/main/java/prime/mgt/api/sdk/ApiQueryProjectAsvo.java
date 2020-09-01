package prime.mgt.api.sdk;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import prime.mgt.api.sdk.valueobject.ProjectVO;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@JsonInclude(Include.NON_NULL)
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiQueryProjectAsvo extends ApiServiceVO {
	@JsonProperty(value = "projects")
	private Set<ProjectVO> projects;

	public Set<ProjectVO> getProjects() {
		return projects;
	}

	public void setProjects(Set<ProjectVO> projects) {
		this.projects = projects;
	}
}
