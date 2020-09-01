package prime.mgt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.domain.enums.ProjectType;
import prime.mgt.domain.enums.Promotions;
import prime.mgt.domain.enums.Stock;
import prime.mgt.service.dto.MarketingDto;
import prime.mgt.service.dto.ProjectDto;
import prime.mgt.service.dto.RetailDto;
import prime.mgt.service.impl.ProjectService;

@Component
public class ApiAddProjectService extends ApiService {
	@Autowired
	private ProjectService projectService;

	@Override
	public ApiServiceVO doAction(RequestHolder requestHolder) throws ApiException {
		ApiServiceVO asvo = new ApiServiceVO();
		ProjectDto projectDto;
		ProjectType projectType = ProjectType.fromString(requestHolder.getProjectType());
		String customerSatisfaction = requestHolder.getCustomerSatisfaction();
		Stock stock = Stock.fromString(requestHolder.getStock());
		String sales = requestHolder.getSales();
		Promotions promotions = Promotions.fromString(requestHolder.getPromotions());
		if (ProjectType.MARKETING.equals(projectType)) {
			MarketingDto marketing = new MarketingDto.Builder().customerSatisfaction(customerSatisfaction).stock(stock).sales(sales).build();
			projectDto = new ProjectDto.Builder().projectType(projectType).marketing(marketing).build();
		} else {
			RetailDto reatilDto = new RetailDto.Builder().promotions(promotions).build();
			projectDto = new ProjectDto.Builder().projectType(projectType).reatil(reatilDto).build();
		}
		projectService.addProject(projectDto);
		asvo.setSuccessResponseParams();
		return asvo;
	}
}
