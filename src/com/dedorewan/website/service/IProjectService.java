package com.dedorewan.website.service;

import java.util.List;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

public interface IProjectService {
	List<Project> findAll();
	Project getProject(Long id);
	void addProject(Project project);
	void addDummyProjects();
	boolean projectNumberExisted(Integer project_number);
	boolean visaExsisted(String visa);
	void updateProject(Project project);
	void deleteProject(Long id);
	void deleteProjects(Long[] ids);
	List<Project> filterProjects(String keywords, STATUS statusKey);
}
