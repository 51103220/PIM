package com.dedorewan.website.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByVisa(String visa);

	@Query("SELECT e FROM Employee e where e.id NOT IN (SELECT g.leader.id FROM Group g)")
	List<Employee> availableEmployee();
}
