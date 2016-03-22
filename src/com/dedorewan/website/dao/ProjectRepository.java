package com.dedorewan.website.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dedorewan.website.dom.Project;
import static com.dedorewan.website.dom.Project.STATUS;

@Repository
public class ProjectRepository implements IProjectRepository {
	public List<Project> fakeList() {
		List<Project> pList = new ArrayList<Project>();
		for (int i = 0; i < 10; i++) {
			Project p = new Project(Long.valueOf(i), Long.valueOf(22), 33 + i,
					"PTQ", "Dedo", STATUS.NEW, new Date(), new Date(), 323232);
			Project p1 = new Project(Long.valueOf(i + 10), Long.valueOf(22), 33,
					"PTQ Never Winter Night Yeah", "Dedo", STATUS.FIN, new Date(), new Date(), 32323222);
			pList.add(p);
			pList.add(p1);
		}
		return pList;
	}

	private List<Project> pList = fakeList();

	public List<Project> findAll() {
		return pList;
	}

	public Project getProject(Long id) {
		for(Project p : pList){
			if(id == p.getId()){
				return p;
			}
		}
		return null;
	}
	public void addProject(Project project) {
		pList.add(project);
	}
	public void addDummyProjects() {

	}
}
