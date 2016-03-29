package com.dedorewan.website.dao;

import java.util.List;

import com.dedorewan.website.dom.Employee;


public interface IEmployeeRepository {
	List<Employee> findAll();
	boolean visaExsisted(String visa);
	Long getEmployeeId(String visa);
	String getEmployeeVisa(Long id);
	List<Employee> availableEmployee();
}
