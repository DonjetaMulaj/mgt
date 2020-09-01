package prime.mgt.service.spec;

import org.springframework.beans.factory.annotation.Autowired;

import prime.mgt.domain.Project;
import prime.mgt.domain.ProjectMarketing;
import prime.mgt.domain.ProjectRetail;
import prime.mgt.domain.enums.ProjectType;
import prime.mgt.domain.spec.ProjectDao;
import prime.mgt.service.dto.MarketingDto;
import prime.mgt.service.dto.ProjectDto;
import prime.mgt.service.dto.RetailDto;
import prime.mgt.service.impl.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;

	@Override
	public void addProject(ProjectDto dto) {
		Project project = new Project();
		MarketingDto marketingDto = dto.getMarketing();
		RetailDto retailDto = dto.getReatil();
		if (ProjectType.MARKETING.equals(dto.getProjectType())) {		
			ProjectMarketing marketing = new ProjectMarketing.Builder().customerSatisfaction(marketingDto.getCustomerSatisfaction()).stock(marketingDto.getStock()).sales(marketingDto.getSales()).build();
			project = new Project.Builder().projectType(dto.getProjectType()).marketing(marketing).build();
		} else {
			ProjectRetail retail = new ProjectRetail.Builder().promotions(retailDto.getPromotions()).build();
			project = new Project.Builder().projectType(dto.getProjectType()).retail(retail).build();
		}
		projectDao.save(project);
	}
	
}
