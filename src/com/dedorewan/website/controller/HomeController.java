package com.dedorewan.website.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dedorewan.website.service.IProjectService;
import com.dedorewan.website.validator.ProjectValidator;

@Controller
public class HomeController {
	@Value("${application.errors.default}")
	private String default_errors;
	@Autowired
	private ProjectValidator projectValidator;
	@Autowired
	private IProjectService projectService;

	

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap model) {
		model.addAttribute("projects", projectService.findAll());
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/errorsunexpected={message}")
	ModelAndView errorPage(@PathVariable String message) {
		ModelAndView model = new ModelAndView("errors");
		model.addObject("message", message);
		return model;
	}

}
