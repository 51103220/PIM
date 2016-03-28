package com.dedorewan.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dedorewan.website.service.IProjectService;

@Controller

public class HomeController {
	@Value("${application.errors.default}")
	private String default_errors;

	@Autowired
	private IProjectService projectService;
	@Value("${projects.maxProjectPerPage}")
	Integer projectsPerPage;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("projects", projectService.projectsInPage(projectService.findAll(), 1));
		model.addObject("pages", projectService.numberPages(projectService.findAll(), projectsPerPage));
		model.addObject("isSearchResult", false);
		model.addObject("selected",1);
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/errorsunexpected={message}")
	ModelAndView errorPage(@PathVariable String message) {
		ModelAndView model = new ModelAndView("errors");
		model.addObject("message", message);
		return model;
	}

}
