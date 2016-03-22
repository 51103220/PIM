package com.dedorewan.website.dao;

import java.util.List;

import com.dedorewan.website.dom.Project;

public interface IProjectRepository {
	List<Project> findAll();
	Project getProject(Long id);
	void addProject(Project project);
	void addDummyProjects();
}
