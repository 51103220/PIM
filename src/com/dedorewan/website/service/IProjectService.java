package com.dedorewan.website.service;

import java.util.List;
import java.util.TreeSet;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;

public interface IProjectService {
	List<Project> findAll();
	List<Project> findAllSearchResults();
	TreeSet<Project> projectsInPage(List<Project> projects,Integer page);
	Project getProject(Long id);
	void addProject(Project project);
	boolean projectNumberExisted(Integer project_number);
	boolean visaExsisted(String visa);
	void updateProject(Project project);
	void deleteProject(Long id);
	void deleteProjects(Long[] ids);
	Integer numberPages(List<Project> projects, Integer maxProjects);
	TreeSet<Project> filterProjects(String keywords, STATUS statusKey);
	String groupLeaderVisa(Project project);
}
