package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

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
public class ProjectRepository implements IProjectRepository {
	@Value("${projects.maxProjectPerPage}")
	Integer projectsPerPage;

	public List<Project> fakeList() {
		List<Project> pList = new ArrayList<Project>();
		for (int i = 0; i < 10; i++) {
			Project p = new Project(Long.valueOf(i), Long.valueOf(1 + i), 30 + i, "PTQ", "Dedo", STATUS.NEW, new Date(),
					new Date(), 323232);
			Project p1 = new Project(Long.valueOf(i + 10), Long.valueOf(22), 60 + i, "PTQ Never Winter Night Yeah",
					"Dedo", STATUS.FIN, new Date(), new Date(), 32323222);
			pList.add(p);
			pList.add(p1);
		}
		return pList;
	}

	public List<String> fakeVisas() {
		List<String> visas = new ArrayList<String>();
		visas.add("THY");
		visas.add("NQN");
		visas.add("VQB");
		return visas;

	}

	private List<Project> pList = fakeList();
	private List<String> visas = fakeVisas();
	private List<Project> searchResults = new ArrayList<Project>();

	public List<Project> findAll() {
		return pList;
	}

	public List<Project> findAllSearchResults() {
		return searchResults;
	}

	public Project getProject(Long id) {
		for (Project p : pList) {
			if (id == p.getId()) {
				return p;
			}
		}
		return null;
	}

	public void addProject(Project project) {
		pList.add(project);
	}

	public void addDummyProjects() {

	}

	public boolean projectNumberExisted(Integer project_number) {
		for (Project p : pList) {
			if (project_number == p.getProjectNumber()) {
				return true;
			}
		}
		return false;
	}

	public boolean visaExsisted(String visa) {
		for (String v : visas) {
			if (visa.equals(v)) {
				return true;
			}
		}
		return false;
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

	public TreeSet<Project> filterProjects(String keywords, STATUS statusKey) {
		TreeSet<Project> filterResult = new TreeSet<Project>(new SortedFilterProjects());
		searchResults.clear();
		for (Project p : pList) {
			if (p.getStatus() == statusKey) {
				searchResults.add(p);
				filterResult.add(p);
			} else if (matchedByKeywords(p, keywords)) {
				searchResults.add(p);
				filterResult.add(p);
			}
		}
		return filterResult;
	}

	private Boolean matchedByKeywords(Project project, String keywords) {
		if (!keywords.isEmpty()) {
			if (keywords.matches("^[0-9]+")) {
				Integer number = -1;
				try {
					number = Integer.parseInt(keywords);
				} catch (NumberFormatException e) {

				}
				if (project.getProjectNumber() == number) {
					return true;
				}
			} else {
				if (project.getName().contains(keywords) || project.getCustomer().contains(keywords)) {
					return true;
				}
			}
		}
		return false;
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
}
