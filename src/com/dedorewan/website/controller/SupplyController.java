package com.dedorewan.website.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SupplyController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/newSupply")
	@ResponseBody
	ModelAndView newSupplyPage() {
		ModelAndView model = new ModelAndView("forms/newSupply");
		return model;
	}
}
