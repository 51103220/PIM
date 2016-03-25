package com.dedorewan.website.controller;

import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dedorewan.website.dom.Project;
import com.dedorewan.website.dom.Project.STATUS;
import com.dedorewan.website.domain.JsonResponse;
import com.dedorewan.website.service.IProjectService;
import com.dedorewan.website.validator.ProjectValidator;

@Controller
public class ProjectController {

	@Autowired
	private ProjectValidator projectValidator;
	@Autowired
	private IProjectService projectService;
	@Autowired
	JsonResponse jsonResponse;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(projectValidator);
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
		model.addObject("formName", "New");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/NewProject")
	@ResponseBody
	public JsonResponse newProject(@Validated @RequestBody Project project,
			BindingResult result) {

		if (result.hasErrors()) {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getFieldErrors());
		} else {
			projectService.addProject(project);
			jsonResponse.setStatus("SUCCESS");
		}
		return jsonResponse;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/project/{id}/detail")
	@ResponseBody
	public ModelAndView getProject(@PathVariable Long id) {
		Project project = projectService.getProject(id);
		ModelAndView model = new ModelAndView("forms/newProject");
		model.addObject("formName", "Edit");
		model.addObject("project", project);
		return model;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/resetCriteria")
	@ResponseBody
	public ModelAndView resetCriteria(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("forms/projectList");
		model.addObject("projects", projectService.findAll());
		request.getSession().setAttribute("searchValue", "");
		return model;
	}


	@RequestMapping(method = RequestMethod.POST, value = "/filterProject")
	@ResponseBody
	public ModelAndView filterProjects(
			@RequestParam(value = "keywords") String keywords,
			@RequestParam(value = "statusKey") STATUS statusKey,
			HttpServletRequest request) {
		TreeSet<Project> filterResult = projectService.filterProjects(keywords,
				statusKey);
		ModelAndView model = new ModelAndView("forms/projectList");
		model.addObject("projects", filterResult);
		request.getSession().setAttribute("searchValue", keywords);
		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/project/{id}/delete")
	@ResponseBody
	public String deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return "success";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMultiple")
	@ResponseBody
	public String deleteProjects(@RequestParam(value = "ids[]") Long[] ids) {
		projectService.deleteProjects(ids);
		return "success";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/EditProject")
	@ResponseBody
	public JsonResponse editProject(@Validated @RequestBody Project project,
			BindingResult result) {

		if (result.hasErrors()) {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getFieldErrors());
		} else {
			projectService.updateProject(project);
			jsonResponse.setStatus("SUCCESS");
		}
		return jsonResponse;
	}
}