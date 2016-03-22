package com.dedorewan.website.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dedorewan.website.dom.Project;

@Component
public class ProjectValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Project project = (Project) target;
		if (project.getName().length()<5) {
			errors.rejectValue("name","", "Username length is less than 5");
		}
	}
}
