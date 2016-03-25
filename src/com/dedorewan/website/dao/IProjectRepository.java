package com.dedorewan.website.dao;

import java.util.List;
import java.util.TreeSet;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

public interface IProjectRepository {
	List<Project> findAll();
	Project getProject(Long id);
	void addProject(Project project);
	void addDummyProjects();
	boolean projectNumberExisted(Integer project_number);
	boolean visaExsisted(String visa);
	void updateProject(Project project);
	void deleteProject(Long id);
	void deleteProjects(Long[] ids);
	TreeSet<Project> filterProjects(String keywords, STATUS statusKey);
}
