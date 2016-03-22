package com.dedorewan.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dedorewan.website.service.IProjectService;
import com.dedorewan.website.validator.ProjectValidator;

@Controller
public class ProjectController {
	@Autowired
	private ProjectValidator projectValidator;
	@Autowired
	private IProjectService projectService;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(projectValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "start_date",
				new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listProject")
	@ResponseBody
	ModelAndView listProjectPage() {
		ModelAndView model = new ModelAndView("forms/projectList");
		model.addObject("projects", projectService.findAll());
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/newProject")
	@ResponseBody
	ModelAndView newProjectPage() {
		ModelAndView model = new ModelAndView("forms/newProject");
		return model;
	}
}
