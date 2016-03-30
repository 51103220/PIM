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
		if (project.getProjectNumber() == null) {
			errors.rejectValue("projectNumber", "",
					"Project Number must not be left empty");
		} else if (project.getProjectNumber() <= 0) {
			errors.rejectValue("projectNumber", "",
					"Project Number must be greater than 0");
		} else if (project.getProjectNumber() >= 10000) {
			errors.rejectValue("projectNumber", "",
					"Project Number must be 4 digits");
		}
		if (project.getName().length() == 0) {
			errors.rejectValue("name", "",
					"Project Name must not be left empty");
		}
		if (project.getCustomer().length() == 0) {
			errors.rejectValue("customer", "",
					"Customer Name must not be left empty");
		}
		if (project.getGroupId() == null) {
			errors.rejectValue("groupId", "", "Must choose a group");
		}
		if (project.getStartDate() == null) {
			errors.rejectValue("startDate", "", "Must choose a startingDate");
		}
		if (projectService.getProject(project.getId()) == null
				&& projectService.projectNumberExisted(project
						.getProjectNumber())) {
			errors.rejectValue("projectNumber", "projectNumberExisted",
					"The project number already existed. Please select a different project number");
		}
		if (!visas.toString().equals("")) {
			errors.rejectValue("members", "InvalidVisa",
					"The following visas do not exist:{" + visas.toString()
							+ "}");
		}

	}
}
