package com.dedorewan.website.service;

import java.util.HashMap;
import java.util.List;

import com.dedorewan.website.dom.Group;
public interface IGroupService {
	List<Group> findAll();
	HashMap<Long,String> groupLeaders();

}
