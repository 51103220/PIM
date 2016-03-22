package com.dedorewan.website.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProjectController {
	@RequestMapping(method = RequestMethod.GET, value = "/listProject")
	@ResponseBody
	ModelAndView listProjectPage() {
		ModelAndView model = new ModelAndView("forms/projectList");
		return model;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/newProject")
	@ResponseBody
	ModelAndView newProjectPage() {
		ModelAndView model = new ModelAndView("forms/newProject");
		return model;
	}
}
