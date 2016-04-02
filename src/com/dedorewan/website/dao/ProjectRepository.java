package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

class SortedFilterProjects implements Comparator<Project> {

	@Override
	public int compare(Project p1, Project p2) {
		if (p1.getProjectNumber() == p2.getProjectNumber()) {
			return 0;
		} else if (p1.getProjectNumber() > p2.getProjectNumber()) {
			return 1;
		} else {
			return -1;
		}
	}
}

@Repository
public class ProjectRepository extends AbstractDao<Long, Project>implements IProjectRepository {
	@Value("${projects.maxProjectPerPage}")
	Integer projectsPerPage;
	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IGroupRepository groupRepository;

	private List<Project> pList = new ArrayList<Project>();

	private List<Project> searchResults = new ArrayList<Project>();

	@SuppressWarnings("unchecked")
	public List<Project> findAll() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("projectNumber"));
		pList = (List<Project>) criteria.list();
		return pList;
	}

	public List<Project> findAllSearchResults() {
		return searchResults;
	}

	public Project getProject(Long id) {
		Project project = null;
		if (id != null) {
			Project result = getByKey(id);
			if (result != null) {
				project = new Project(result.getId(), result.getGroupId(), result.getProjectNumber(), result.getName(),
						result.getCustomer(), result.getStatus(), result.getEndDate(), result.getEndDate(),
						result.getVersion());
				project.setEmployees(result.getEmployees());
				project.setGroup(result.getGroup());
			}
		}
		return project;
	}

	public void addProject(Project project) {
		project.setVersion(2050512000);
		project.setGroup(groupRepository.getGroup(project.getGroupId()));
		project.setEmployees(employeeRepository.getEmployees(project.getMembers()));
		getSession().merge(project);
	}

	public void addDummyProjects() {

	}

	public boolean projectNumberExisted(Integer project_number) {
		Criteria criteria = createEntityCriteria().add(Restrictions.eq("projectNumber", project_number));
		if (criteria.list().size() > 0) {
			return true;
		}
		return false;
	}

	public boolean visaExsisted(String visa) {
		return employeeRepository.visaExsisted(visa);
	}

	public void updateProject(Project project) {
		for (Project p : pList) {
			if (p.getId() == project.getId()) {
				p.editData(project);
				break;
			}
		}
	}

	public void deleteProject(Long id) {
		for (Project p : pList) {
			if (p.getId() == id && p.getStatus() == STATUS.NEW) {
				pList.remove(p);
				break;
			}
		}
	}

	public void deleteProjects(Long[] ids) {
		for (Long id : ids) {
			deleteProject(id);
		}
	}

	@SuppressWarnings("unchecked")
	public TreeSet<Project> filterProjects(String keywords, STATUS statusKey) {
		TreeSet<Project> filterResult = new TreeSet<Project>(new SortedFilterProjects());
		searchResults.clear();
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("projectNumber"));
		Criterion condition;

		if (keywords.matches("^[0-9]+")) {
			Integer projectNumber = -1;
			projectNumber = Integer.parseInt(keywords);
			condition = Restrictions.eq("projectNumber", projectNumber);
		} else {
			condition = Restrictions.or(Restrictions.ilike("name", "%" + keywords + "%", MatchMode.END),
					Restrictions.ilike("customer", "%" + keywords + "%", MatchMode.END));
		}

		searchResults = (List<Project>) criteria.add(Restrictions.and(condition, Restrictions.eq("status", statusKey)))
				.list();
		return filterResult;
	}

	public TreeSet<Project> projectsInPage(List<Project> pList, Integer page) {
		TreeSet<Project> projects = new TreeSet<Project>(new SortedFilterProjects());
		Integer start_index = (page - 1) * 5;
		Integer end_index = page * projectsPerPage;
		for (Integer i = 0; i < pList.size(); i++) {
			if (i >= start_index && i < end_index) {
				projects.add(pList.get(i));
			}
		}
		return projects;
	}

	public Integer numberPages(List<Project> projects, Integer maxProjects) {
		Integer pages = 0;
		if (projects.size() % maxProjects == 0) {
			pages = projects.size() / maxProjects;
		} else {
			pages = projects.size() / maxProjects + 1;
		}
		return pages;
	}

	public String groupLeaderVisa(Project project) {
		return groupRepository.groupLeaderVisa(project.getGroupId());
	}
}
