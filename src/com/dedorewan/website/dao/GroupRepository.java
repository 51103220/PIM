package com.dedorewan.website.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Group;

@Repository
public class GroupRepository extends AbstractDao<Long, Group> implements
		IGroupRepository {
	@Autowired
	private IEmployeeRepository employeeRepository;

	private List<Group> gList;

	@SuppressWarnings("unchecked")
	public List<Group> findAll() {
		Criteria criteria = createEntityCriteria();
		gList = (List<Group>) criteria.list();
		return gList;
	}

	public HashMap<Long, String> groupLeaders() {
		HashMap<Long, String> map = new HashMap<Long, String>();
		gList = findAll();
		for (Group g : gList) {
			String visa = g.getLeader().getVisa();
			map.put(g.getId(), visa);
		}
		return map;
	}

	public String groupLeaderVisa(Long id) {
		return employeeRepository.getEmployeeVisa(id);
	}

	public Long getGroupId(Long groupLeaderId) {
		Long id = Long.valueOf(-1);
		return id;
	}
}
