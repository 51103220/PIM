package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Group;

@Repository
public class GroupRepository implements IGroupRepository {
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
}
