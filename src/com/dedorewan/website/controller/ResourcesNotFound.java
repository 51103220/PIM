package com.dedorewan.website.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFound extends NoSuchRequestHandlingMethodException {

	public ResourcesNotFound(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855387022034827639L;

}
