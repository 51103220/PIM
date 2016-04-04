package com.dedorewan.website.dao;

import java.util.HashMap;
import java.util.List;

import com.dedorewan.website.dom.Group;


public interface IGroupRepository {
	List<Group> findAll();
	HashMap<Long,String> groupLeaders();
	String groupLeaderVisa(Long id);
	Long getGroupId(Long groupLeaderId);
	Group getGroup(Long key);
}
