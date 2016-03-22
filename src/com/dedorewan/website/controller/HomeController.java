package com.dedorewan.website.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Value("${application.errors.default}")
	private String default_errors;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap model) {

		model.addAttribute("greeting", "PIM INITIALIZER");
		return "index";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/backToHome")
	public String backToHome(ModelMap model) {

		model.addAttribute("greeting", "PIM INITIALIZER");
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/errors")
	ModelAndView errorPage() {
		ModelAndView model = new ModelAndView("errors");
		model.addObject("message", default_errors);
		return model;
	}

}
