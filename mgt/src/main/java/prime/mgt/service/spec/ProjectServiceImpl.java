package prime.mgt.service.spec;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.domain.Project;
import prime.mgt.domain.spec.ProjectDao;
import prime.mgt.service.impl.ProjectService;

@Component
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectDao projectDao;

	@Override
	public Set<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}
}
