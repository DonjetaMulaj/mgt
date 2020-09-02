package prime.mgt.domain.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.domain.Project;
import prime.mgt.domain.ProjectItems;
import prime.mgt.domain.User;
import prime.mgt.domain.enums.ItemStatus;
import prime.mgt.domain.enums.ItemType;
import prime.mgt.domain.spec.ProjectDao;
import prime.mgt.domain.spec.UserDao;

@Component
public class ProjectDaoImpl implements ProjectDao {
	@Autowired
	UserDao userDao;

	@Override
	public Set<Project> getAllProjects() {
		User user = userDao.getUser();
		Set<ProjectItems> items = getProjectItems(user);
		//root projects
		Project marketing = new Project.Builder().id("marketingId").projectItems(items).user(user).build();
		Project retail = new Project.Builder().id("retailId").projectItems(items).user(user).build();
		//retails child projects
		Project sales = new Project();
		Project stock = new Project();
		Project customerSatisfaction = new Project();
		retail.setChildProject(new HashSet<Project>(Arrays.asList(sales, stock, customerSatisfaction)));
		//marketings childPoject (1st hierarchy of marketing)
		Project promotion = new Project.Builder().id("promotionId").projectItems(items).user(user).parentProjectId("marketingId").build();
		//promotions child project (2nd hierarchy of marketing)
		Project campaigns = new Project.Builder().id("campaignsId").projectItems(items).user(user).parentProjectId("promotionId").build();
		//campaigns child projects (3d hierarchy of marketing)
		Project themes = new Project.Builder().id("themesId").projectItems(items).user(user).parentProjectId("campaignsId").build();
		campaigns.setChildProject(new HashSet<Project>(Arrays.asList(themes)));
		promotion.setChildProject(new HashSet<Project>(Arrays.asList(campaigns)));
		marketing.setChildProject(new HashSet<Project>(Arrays.asList(promotion)));
		return new HashSet<Project>(Arrays.asList(marketing, retail));
	}

	private Set<ProjectItems> getProjectItems(User user) {
		Set<ProjectItems> items = new HashSet<ProjectItems>();
		ProjectItems pi = new ProjectItems();
		pi.setItemStatus(ItemStatus.IN_PROGRESS);
		pi.setItemType(ItemType.NOTE);
		pi.setAssigneeId(user);
		pi.setReporterId(user);
		items.add(pi);
		return items;
	}
}
