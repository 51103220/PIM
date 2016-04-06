package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private SessionFactory sessionFactory;
	@Value("${projects.maxProjectPerPage}")
	Integer projectsPerPage;
	private List<Project> searchResult = new ArrayList<Project>();
	private List<Project> editingProjects = new ArrayList<Project>();

	private void deleteEdittingProjects(Long id) {
		for (Project p : editingProjects) {
			if (p.getId() == id) {
				editingProjects.remove(p);
				break;
			}
		}
	}

	private void updateEdittingProjects(Long id) {
		for (Project p : editingProjects) {
			if (p.getId() == id) {
				p.setVersion(p.getVersion() + 1);
				break;
			}
		}
	}

	private Project getEdditingProjects(Long id) {
		for (Project p : editingProjects) {
			if (p.getId() == id) {
				return p;

			}
		}
		return null;
	}

	public void addEdittingProjects(Project project) {
		for (Project p : editingProjects) {
			if (p.getId() == project.getId())
				return;
		}
		editingProjects.add(project);
	}

	@SuppressWarnings("unchecked")
	public List<Project> filterProjects(String keywords, STATUS statusKey) {
		String query;
		if (keywords.matches("^[0-9]+")) {
			Integer projectNumber = -1;
			projectNumber = Integer.parseInt(keywords);
			query = "select p from Project p where project_number = "
					+ projectNumber + "and status = '" + statusKey
					+ "' order by project_number ASC";
		} else {
			keywords = keywords.toLowerCase();
			query = "select p from Project p where LOWER(name) like '%"
					+ keywords + "%' or LOWER(customer) like '%" + keywords
					+ "%' and status ='" + statusKey
					+ "' order by project_number ASC";
		}
		searchResult = (List<Project>) entityManager.createQuery(query)
				.getResultList();
		return searchResult;
	}

	public List<Project> findAllSearchResults() {
		return searchResult;
	}

	public List<Project> projectsInPage(List<Project> pList, Integer page) {
		List<Project> projects = new ArrayList<Project>();
		Integer start_index = (page - 1) * projectsPerPage;
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

	public boolean visaExsisted(String visa) {
		if (employeeRepository.findByVisa(visa).size() > 0) {
			return true;
		}
		return false;
	}

	public void insert(Project project) throws Exception {
		project.setVersion(2050512000);
		project.setGroup(groupRepository.getOne(project.getGroupId()));
		project.setEmployees(employeeRepository.getAllEmployeeByVisa(project
				.getMembers()));
		Session session = sessionFactory.getCurrentSession();
		session.merge(project);
	}

	public void update(Project project) throws Exception {
		project.setGroup(groupRepository.getOne(project.getGroupId()));
		project.setEmployees(employeeRepository.getAllEmployeeByVisa(project
				.getMembers()));
		Session session = sessionFactory.getCurrentSession();
		// Project existing_project = (Project) session.get(Project.class,
		// project.getId());
		Project existing_project = getEdditingProjects(project.getId());
		if (existing_project != null) {
			existing_project.updateData(project);
			if (project.getVersion().equals(existing_project.getVersion())) {
				session.merge(existing_project);
				updateEdittingProjects(project.getId());
			} else {
				throw new CustomException("999",
						"UPDATE FAILED: PROJECT HAS BEEN USING BY ANOTHER PROCESS");
			}
		} else {
			throw new CustomException("999",
					"UPDATE FAILED: PROJECT HAS BEEN DELETED");
		}
	}

	public void delete(Long id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Project existing_project = (Project) session.get(Project.class, id);
		if (existing_project != null
				&& existing_project.getStatus() == STATUS.NEW) {
			existing_project.setGroup(null);
			existing_project.getEmployees().clear();
			deleteEdittingProjects(id);
			session.delete(existing_project);
		} else {
			throw new CustomException("1000",
					"DELETE FAILED: PROJECT DOES NOT EXIST");
		}

	}
}
