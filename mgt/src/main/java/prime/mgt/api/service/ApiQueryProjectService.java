package prime.mgt.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiQueryProjectAsvo;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.api.sdk.valueobject.ProjectItemsVO;
import prime.mgt.api.sdk.valueobject.ProjectVO;
import prime.mgt.api.sdk.valueobject.UserVO;
import prime.mgt.domain.Project;
import prime.mgt.domain.ProjectItems;
import prime.mgt.domain.User;
import prime.mgt.service.impl.ProjectService;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@Component
public class ApiQueryProjectService extends ApiService {
	@Autowired
	ProjectService projectService;

	@Override
	public ApiServiceVO doAction(RequestHolder requestHolder) throws ApiException {
		ApiQueryProjectAsvo asvo = new ApiQueryProjectAsvo();
		Set<Project> projects = projectService.getAllProjects();
		Set<ProjectVO> projectsVO = new HashSet<ProjectVO>();
		projects.forEach(p -> {
			User user = p.getUser();
			UserVO userVO = new UserVO.Builder().userName(user.getUserName()).userRoles(user.getUserRoles()).build();
			Set<Project> childProject = p.getChildProject();
			Set<ProjectItems> pi = p.getProjectItems();
			Set<ProjectItemsVO> piVO = new HashSet<>();
			Set<ProjectVO> childProjectVO = new HashSet<>();
			pi.forEach(item -> {
				ProjectItemsVO pItemsVo = new ProjectItemsVO.Builder().assigneeId(userVO).itemStatus(item.getItemStatus()).itemType(item.getItemType())
						.title(item.getTitle()).build();
				piVO.add(pItemsVo);
			});
			childProject.forEach(chP -> {
				ProjectVO pVO = new ProjectVO.Builder().projectName(chP.getProjectName()).projectDescription(chP.getProjectDescription())
						.projectCreatedTs(chP.getProjectCreatedTs()).parentProjectId(chP.getParentProjectId()).build();
				childProjectVO.add(pVO);
			});
			ProjectVO pVO = new ProjectVO.Builder().projectName(p.getProjectName()).projectDescription(p.getProjectDescription())
					.projectCreatedTs(p.getProjectCreatedTs()).parentProjectId(p.getParentProjectId()).projectItems(piVO).childProject(childProjectVO).build();
			projectsVO.add(pVO);
		});
		asvo.setProjects(projectsVO);
		asvo.setSuccessResponseParams();
		return asvo;
	}
}
