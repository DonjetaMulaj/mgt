package prime.mgt.domain.spec;

import java.util.Set;

import org.springframework.stereotype.Component;

import prime.mgt.domain.Project;
@Component
public interface ProjectDao extends GenericDao<Project, String> {
	
	Set<Project> getAllProjects();
	
}
