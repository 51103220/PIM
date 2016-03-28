package com.dedorewan.website.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectEmployee {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false)
	private Long projectId;
	@NotNull
	@Column(nullable = false)
	private Long employeeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long id) {
		this.projectId = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long id) {
		this.employeeId = id;
	}

	public ProjectEmployee() {

	}

	public ProjectEmployee(Long id, Long employeeId, Long projectId) {
		this.id = id;
		this.employeeId = employeeId;
		this.projectId = projectId;
	}
}
