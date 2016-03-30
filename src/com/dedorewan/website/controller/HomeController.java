package com.dedorewan.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.service.IProjectService;

@Controller
public class HomeController {
	@Value("${application.errors.default}")
	private String default_errors;

	@Autowired
	private IProjectService projectService;
	@Value("${projects.maxProjectPerPage}")
	Integer projectsPerPage;
	private static final int FIRST_PAGE = 1;
	private static final int DEFAULT_SELECTED = 1;

	public ModelAndView makeProjectModel(String view,
			List<Project> projectList, Integer page, Integer selectedPage,
			Boolean isSearchResult) {
		ModelAndView model = new ModelAndView(view);
		model.addObject("projects",
				projectService.projectsInPage(projectList, page));
		model.addObject("pages",
				projectService.numberPages(projectList, projectsPerPage));
		model.addObject("isSearchResult", isSearchResult);
		model.addObject("selected", selectedPage);
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView index() {
		ModelAndView model = makeProjectModel("index",
				projectService.findAll(), FIRST_PAGE, DEFAULT_SELECTED, false);
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/errorsunexpected={message}")
	public ModelAndView errorPage(@PathVariable String message) {
		ModelAndView model = new ModelAndView("errors");
		model.addObject("message", message);
		return model;
	}

}
