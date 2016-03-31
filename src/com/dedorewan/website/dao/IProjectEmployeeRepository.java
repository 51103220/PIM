package com.dedorewan.website.dao;

import java.util.List;

import com.dedorewan.website.dom.ProjectEmployee;

public interface IProjectEmployeeRepository {
	List<ProjectEmployee> findAll();
	void addProjectEmployee(Long projectId, Long employeeId);
}
