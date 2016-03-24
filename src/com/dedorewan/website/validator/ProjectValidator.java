package com.dedorewan.website.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.service.IProjectService;

@Component
public class ProjectValidator implements Validator {
	@Autowired
	private IProjectService projectService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Project project = (Project) target;
		StringBuilder visas = new StringBuilder("");
		boolean first = true;
		for (String visa : project.getMembers()) {
			if (!projectService.visaExsisted(visa)) {
				if (first) {
					visas.append(visa);
					first = false;
				} else {
					visas.append("," + visa);
				}
			}
		}
		if (project.getName().length() < 5) {
			errors.rejectValue("name", "", "Username length is less than 5");
		}
		if (projectService.projectNumberExisted(project.getProjectNumber())) {
			errors.rejectValue("projectNumber", "projectNumberExisted",
					"The project number already existed. Please select a different project number");
		}
		if(!visas.toString().equals("")){
			errors.rejectValue("members", "InvalidVisa",
					"The following visas do not exist:{"+ visas.toString() +"}");
		}
		
		

	}
}
