<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="projectList">
	<p class="formName">Project List</p>
	
	<div class="formContent">
		<form class="form-inline" role="form" id="searchInputs">
			<div class="form-group">
				<input type="text" class="form-control"
					placeholder="project number, name, customer name">
			</div>
			<div class="form-group">
				<select class="form-control empty">
					<option value="" selected disabled>Project status</option>
					<option>New</option>
					<option>Finished</option>
					<option>Planned</option>
					<option>In progress</option>
				</select>

			</div>
			<div class="form-group">
				<button type="button" id="search_btn" class="btn btn-primary">Search
					Project</button>
			</div>
			<div class="form-group">
				<a href="#" id="reset_btn"> Reset Search</a>
			</div>
		</form>
		<table class="table" id="searchDatas">
			<thead>
				<tr>
					<th></th>
					<th>Number</th>
					<th>Name</th>
					<th>Status</th>
					<th>Customer</th>
					<th>Start Date</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects}" var="project">
					<tr>
						<td align="center"><input type="checkbox" class="checkIcon"
							value=""></td>
						<td align="right">${project.getProjectNumber()}</td>
						<td>${project.getName()}</td>
						<td>${project.getStatus().getValue()}</td>
						<td>${project.getCustomer()}</td>
						<fmt:formatDate value="${project.getStartDate()}" var="dateString"
							pattern="dd.MM.yyyy" />
						<td>${dateString}</td>
						<td align="center"><a href="#" class="deleteIcon"> <c:choose>
									<c:when test="${project.isNew()}">
										<span class="glyphicon glyphicon-trash"></span>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<ul class="pagination">
		<li><a href="#"><img id="logo"
				src="resources/images/previous_page.png"></a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#"><img id="logo"
				src="resources/images/nextpage_icon.png"></a></li>
	</ul>
	
</div>
<spring:url value="/resources/js/main.js" var="mainJS" />
<script src="${mainJS}"></script>
