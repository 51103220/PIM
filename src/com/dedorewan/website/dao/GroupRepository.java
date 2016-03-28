package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Group;

@Repository
public class GroupRepository implements IGroupRepository {
	@Autowired
	private IEmployeeRepository employeeRepository;
	public List<Group> generateList() {
		List<Group> gList = new ArrayList<Group>();
		for (int i = 0; i < 5; i++) {
			gList.add(new Group(Long.valueOf(i), Long.valueOf(i + 10), Long
					.valueOf(1222 + i)));
		}
		return gList;
	}

	List<Group> gList = generateList();

	public List<Group> findAll() {
		return gList;
	}
	public HashMap<Long,String> groupLeaders(){
		HashMap<Long, String> map = new HashMap<Long, String>();
		for(Group g : gList){
			String visa = employeeRepository.getEmployeeVisa(g.getGroupLeaderId());
			map.put(g.getId(), visa);
		}
		return map;
	}
	public String groupLeaderVisa(Long id){
		return employeeRepository.getEmployeeVisa(id);
	}
	public Long getGroupId(Long groupLeaderId){
		Long id = Long.valueOf(-1);
		for(Group g : gList){
			if(g.getGroupLeaderId() == groupLeaderId){
				id = g.getId();
			}
		}
		return id;
	}
}
