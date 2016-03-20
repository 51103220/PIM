package com.dedorewan.website.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {
	@ExceptionHandler(ResourcesNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResourceNotFound(HttpServletRequest req){
		return "404";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap model) {
		
		model.addAttribute("greeting", "PIM INITIALIZER");
		return "index";
	}
	
}
