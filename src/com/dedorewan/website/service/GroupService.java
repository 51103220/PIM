package com.dedorewan.website.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedorewan.website.dao.IGroupRepository;
import com.dedorewan.website.dom.Group;

@Service
public class GroupService implements IGroupService {
	@Autowired
	private IGroupRepository groupRepository;

	public List<Group> findAll() {
		return groupRepository.findAll();
	}
	public HashMap<Long,String> groupLeaders(){
		return groupRepository.groupLeaders();
	}
}
