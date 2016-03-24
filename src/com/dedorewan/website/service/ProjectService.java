package com.dedorewan.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedorewan.website.dao.IProjectRepository;
import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

@Service
public class ProjectService implements IProjectService {
	@Autowired
	private IProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project getProject(Long id) {
		return projectRepository.getProject(id);
	}

	public void addDummyProjects() {
		projectRepository.addDummyProjects();
	}

	public void addProject(Project project) {
		projectRepository.addProject(project);
	}

	public boolean projectNumberExisted(Integer project_number) {
		return projectRepository.projectNumberExisted(project_number);
	}

	public boolean visaExsisted(String visa) {
		return projectRepository.visaExsisted(visa);
	}
	public void updateProject(Project project){
		projectRepository.updateProject(project);
	}
	public void deleteProject(Long id){
		projectRepository.deleteProject(id);
	}
	public void deleteProjects(Long[] ids){
		projectRepository.deleteProjects(ids);
	}
	public List<Project> filterProjects(String keywords, STATUS statusKey){
		return projectRepository.filterProjects(keywords, statusKey);
	}
}
