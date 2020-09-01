package prime.mgt.service.impl;

import java.util.Set;

import org.springframework.stereotype.Component;

import prime.mgt.domain.Project;
@Component
public interface ProjectService {
	
	Set<Project> getAllProjects();
}
