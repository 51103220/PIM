package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Employee;

@Repository
public class EmployeeRepository extends AbstractDao<Integer, Employee>
		implements IEmployeeRepository {
	@Autowired
	private IGroupRepository groupRepository;

	List<Employee> eList;

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		Criteria criteria = createEntityCriteria();
		eList = (List<Employee>) criteria.list();
		return eList;
	}

	public boolean visaExsisted(String visa) {
		eList = findAll();
		for (Employee e : eList) {
			if (e.getVisa().equals(visa)) {
				return true;
			}
		}
		return false;
	}

	public Long getEmployeeId(String visa) {
		Long id = Long.valueOf(-1);
		eList = findAll();
		for (Employee e : eList) {
			if (e.getVisa().equals(visa)) {
				id = e.getId();
			}
		}
		return id;
	}

	public String getEmployeeVisa(Long id) {
		eList = findAll();
		for (Employee e : eList) {
			if (e.getId() == id) {
				return e.getVisa();
			}
		}
		return null;
	}

	public List<Employee> availableEmployee() {
		List<Employee> availableList = new ArrayList<Employee>();
		
		return availableList;
	}
}
