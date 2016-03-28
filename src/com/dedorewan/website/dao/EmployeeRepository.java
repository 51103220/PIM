package com.dedorewan.website.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Employee;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
	public List<Employee> generateList() {
		List<Employee> eList = new ArrayList<Employee>();
		String[] visas = { "THY", "NQN", "DED", "REW", "AKG", "THP", "YQN",
				"EED", "WEW", "MKG" };
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date date  = new Date();
	    try {
			date = sdf.parse("28.01.1993");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			eList.add(new Employee(Long.valueOf(i), visas[i], "Nguyen", "Tien",
					date, Long.valueOf(1212 + i)));
			eList.add(new Employee(Long.valueOf(i+10), visas[i+5], "Thanh", "Nguyen",
					date, Long.valueOf(1212 + i)));
		}
		return eList;
	}

	List<Employee> eList = generateList();

	public List<Employee> findAll() {
		return eList;
	}
	public boolean visaExsisted(String visa){
		for(Employee e:eList){
			if(e.getVisa().equals(visa)){
				return true;
			}
		}
		return false;
	}
	public Long getEmployeeId(String visa){
		Long id = Long.valueOf(-1);
		for(Employee e: eList){
			if(e.getVisa().equals(visa)){
				id = e.getId();
			}
		}
		return id;
	}
	public String getEmployeeVisa(Long id){
		for(Employee e: eList){
			if(e.getId() == id){
				return e.getVisa();
			}
		}
		return null;
	}
}
