package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import com.dedorewan.website.dom.Employee;

@Repository
public class EmployeeRepository extends AbstractDao<Integer, Employee>implements IEmployeeRepository {
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

	@SuppressWarnings("unchecked")
	public List<Employee> availableEmployee() {
		List<Employee> availableList = new ArrayList<Employee>();
		String sql = "SELECT * FROM EMPLOYEE e WHERE e.id NOT IN(SELECT group_leader_id FROM GROUPS)";
		SQLQuery query = createQuery(sql);
		eList = (List<Employee>) query.list();
		for (Employee e : eList) {
			Employee employee = new Employee(e.getId(), e.getVisa(), e.getFirstName(), e.getLastName(),
					e.getBirthDate(), e.getVersion());
			availableList.add(employee);
		}
		return availableList;
	}
}
