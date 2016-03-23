package com.dedorewan.website.controller;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dedorewan.website.domain.JsonResponse;
import com.dedorewan.website.service.IProjectService;
import com.dedorewan.website.validator.ProjectValidator;

class FUZZ {
	@NotEmpty
	private String name;
	private int id;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

}

@Controller
public class ProjectController {

	@Autowired
	private ProjectValidator projectValidator;
	@Autowired
	private IProjectService projectService;
	@Autowired JsonResponse jsonResponse;
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

	@RequestMapping(method = RequestMethod.POST, value = "/newProject")
	@ResponseBody
	public JsonResponse newProject(@Valid @RequestBody FUZZ project,
			BindingResult result) {
		
		if(result.hasErrors()){
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getFieldErrors());
			
		}
		else{
			jsonResponse.setStatus("SUCCESS");
		}
		return jsonResponse;
	}
}
