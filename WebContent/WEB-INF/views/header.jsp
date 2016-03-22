<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default header">
	<div class="container">
		<a href="<c:url value='/'/>" class="pull-left"><img id="logo"
			src="resources/images/logo_elca.png"> </a>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#headingNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" id="projectName" href="<c:url value='/'/>"><spring:message
					code="application.prjName" /></a>
		</div>
		<div class="collapse navbar-collapse" id="headingNavbar">
			<ul class="nav navbar-nav navbar-right" id="funcOption">
				<li><a href="#" id="helpButton" class="selected">HELP</a></li>
				<li><a href="#" id="logButton">LOGOUT</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="langOption">
				<li><a href="#" class="selected">EN</a></li>
				<li><a href="#">&nbsp;|&nbsp;FR</a></li>
			</ul>
		</div>
	</div>
</nav>
