package com.dedorewan.website.dao;

import java.util.List;

import com.dedorewan.website.dom.Group;


public interface IGroupRepository {
	List<Group> findAll();
}
