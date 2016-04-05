package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;
import com.dedorewan.website.exception.CustomException;

public class IProjectRepositoryImpl implements IProjectRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	IGroupRepository groupRepository;
	private List<Project> searchResult = new ArrayList<Project>();
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Project> filterProjects(String keywords, STATUS statusKey) {
		String query;
		if (keywords.matches("^[0-9]+")) {
			Integer projectNumber = -1;
			projectNumber = Integer.parseInt(keywords);
			query = "select p from Project p where project_number = " + projectNumber + "and status = '" + statusKey
					+ "' order by project_number ASC";
		} else {
			keywords = keywords.toLowerCase();
			query = "select p from Project p where LOWER(name) like '%" + keywords + "%' or LOWER(customer) like '%"
					+ keywords + "%' and status ='" + statusKey + "' order by project_number ASC";
		}
		searchResult = (List<Project>) entityManager.createQuery(query).getResultList();
		return searchResult;
	}

	public List<Project> findAllSearchResults() {
		return searchResult;
	}

	public List<Project> projectsInPage(List<Project> pList, Integer page) {
		List<Project> projects = new ArrayList<Project>();
		Integer start_index = (page - 1) * 5;
		Integer end_index = page * 5;
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

	public boolean visaExsisted(String visa) {
		if (employeeRepository.findByVisa(visa).size() > 0) {
			return true;
		}
		return false;
	}

	public void insert(Project project) {
		project.setVersion(2050512000);
		project.setGroup(groupRepository.getOne(project.getGroupId()));
		project.setEmployees(employeeRepository.getAllEmployeeByVisa(project.getMembers()));
		Session session = sessionFactory.getCurrentSession();
		session.merge(project);
	}

	public void update(Project project) {
		project.setGroup(groupRepository.getOne(project.getGroupId()));
		project.setEmployees(employeeRepository.getAllEmployeeByVisa(project.getMembers()));
		Session session = sessionFactory.getCurrentSession();
		Project existing_project = (Project) session.get(Project.class, project.getId(),
				new LockOptions(LockMode.OPTIMISTIC));
		if (existing_project != null) {
			existing_project.updateData(project);
			session.merge(existing_project);
		} else {
			throw new CustomException("999", "CANT UPDATE PROJECT");

		}
	}

	public void delete(Long id) {

	}
}
