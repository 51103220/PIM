package com.dedorewan.website.service;

import java.util.List;

import com.dedorewan.website.dom.Project;

public interface IProjectService {
	List<Project> findAll();
	Project getProject(Long id);
	void addProject(Project project);
	void addDummyProjects();
}
