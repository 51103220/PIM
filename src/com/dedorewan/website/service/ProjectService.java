package com.dedorewan.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dedorewan.website.dao.IProjectRepository;
import com.dedorewan.website.dom.Project;

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
}
