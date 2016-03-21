<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container" id="main">
	<div class="row">
		<div class="col-md-3" id="menuList">
			<div class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".sidebar-navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<span class="visible-xs navbar-brand">MENU</span>
					 
				</div>
				<div class="navbar-collapse collapse sidebar-navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a class="navbar-brand" href="#">Project List</a></li>
						<li><a class="navbar-brand" href="#">New</a></li>
						<li><a href="#">Project</a></li>
						<li><a href="#">Customer</a></li>
						<li><a href="#">Supply</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-9" id="contentBody">
			<jsp:include page="forms/newProject.jsp" />
		</div>
	</div>
</div>
