package com.dedorewan.website.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dedorewan.website.dao.IProjectRepository;
import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

@Service
@Transactional
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAllByOrderByProjectNumberAsc();
	}

	public List<Project> findAllSearchResults() {
		return projectRepository.findAllSearchResults();
	}

	public Project getProject(Long id) {
		return projectRepository.findOne(id);
	}

	public void addProject(Project project) {
		//projectRepository.save(project)
	}

	public boolean projectNumberExisted(Long id, Integer project_number) {
		List<Project> projects = projectRepository
				.findByProjectNumber(project_number);
		if (projects.size() > 0) {
			if (projects.get(0).getId() == id) {
				return false;
			} else
				return true;
		} else
			return false;
	}

	public boolean visaExsisted(String visa) {
		return projectRepository.visaExsisted(visa);
	}

	public void updateProject(Project project) {
		//customProjectRepository.updateProject(project);
	}

	public void deleteProject(Long id) {
		//customProjectRepository.deleteProject(id);
	}

	public void deleteProjects(Long[] ids) {
		//customProjectRepository.deleteProjects(ids);
	}

	public List<Project> filterProjects(String keywords, STATUS statusKey) {
		return projectRepository.filterProjects(keywords, statusKey);
	}

	public List<Project> projectsInPage(List<Project> projects, Integer page) {
		return projectRepository.projectsInPage(projects, page);
	}

	public Integer numberPages(List<Project> projects, Integer maxProjects) {
		return projectRepository.numberPages(projects, maxProjects);
	}

	public String groupLeaderVisa(Project project) {
		return project.getGroup().getLeader().getVisa();
	}
}
