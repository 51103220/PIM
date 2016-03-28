package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.ProjectEmployee;

@Repository
public class ProjectEmployeeRepository implements IProjectEmployeeRepository {
	public List<ProjectEmployee> generateList(){
		List<ProjectEmployee> peList = new ArrayList<ProjectEmployee>();
		for(int i = 0; i< 5 ; i ++){
			peList.add(new ProjectEmployee(Long.valueOf(i),Long.valueOf(i),Long.valueOf(i)));
		}
		return peList;
	}
	List<ProjectEmployee> peList = generateList();
	public List<ProjectEmployee> findAll() {
		return peList;
	}
	public void addProjectEmployee(Long projectId, Long employeId){
		ProjectEmployee pe = new ProjectEmployee();
		pe.setId(peList.get(peList.size()-1).getId()+1);
		pe.setEmployeeId(employeId);
		pe.setProjectId(projectId);
		peList.add(pe);
	}
}
