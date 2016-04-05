package com.dedorewan.website.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dedorewan.website.dao.IProjectRepository;
import com.dedorewan.website.dao.ICustomProjectRepository;
import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

@Service
@Transactional
public class ProjectService implements IProjectService {
	@Autowired
	private ICustomProjectRepository customProjectRepository;
	@Autowired

	private IProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAllByOrderByProjectNumberAsc();
	}

	public List<Project> findAllSearchResults() {
		return projectRepository.findAllSearchResults();
	}

	public Project getProject(Long id) {
		return customProjectRepository.getProject(id);
	}

	public void addProject(Project project) {
		customProjectRepository.addProject(project);
	}

	public boolean projectNumberExisted(Integer project_number) {
		return customProjectRepository.projectNumberExisted(project_number);
	}

	public boolean visaExsisted(String visa) {
		return customProjectRepository.visaExsisted(visa);
	}

	public void updateProject(Project project) {
		customProjectRepository.updateProject(project);
	}

	public void deleteProject(Long id) {
		customProjectRepository.deleteProject(id);
	}

	public void deleteProjects(Long[] ids) {
		customProjectRepository.deleteProjects(ids);
	}

	public List<Project> filterProjects(String keywords, STATUS statusKey) {
		return projectRepository.filterProjects(keywords, statusKey);
	}

	public List<Project> projectsInPage(List<Project> projects, Integer page) {
		return projectRepository.projectsInPage(projects, page);
	}

	public Integer numberPages(List<Project> projects, Integer maxProjects) {
		return customProjectRepository.numberPages(projects, maxProjects);
	}

	public String groupLeaderVisa(Project project) {
		return customProjectRepository.groupLeaderVisa(project);
	}
}
