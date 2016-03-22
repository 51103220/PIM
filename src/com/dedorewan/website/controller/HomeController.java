package com.dedorewan.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(projectValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "start_date",
				new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap model) {
		model.addAttribute("projects", projectService.findAll());
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/errors")
	ModelAndView errorPage() {
		ModelAndView model = new ModelAndView("errors");
		model.addObject("message", default_errors);
		return model;
	}

}
