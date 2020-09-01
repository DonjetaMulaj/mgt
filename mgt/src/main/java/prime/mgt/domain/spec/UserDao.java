package prime.mgt.domain.spec;

import org.springframework.stereotype.Component;

import prime.mgt.domain.Project;
import prime.mgt.domain.User;

@Component
public interface UserDao extends GenericDao<Project, String> {
	
	User getUser();
}
