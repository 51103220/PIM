<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="links">
    <spring:message code="pagination.maxLinksPerPage" />
</c:set>
<div id="projectList">
	<p class="formName">Project List</p>

	<div class="formContent">
		<form class="form-inline" action="filterProject" role="form"
			id="searchInputs">
			<div class="form-group">
				<input type="text" id="keywords" class="form-control"
					placeholder="project number, name, customer name"
					value="${searchValue}">
			</div>
			<div class="form-group">
				<select class="form-control empty" id="statusKey">
					<option value="" selected disabled>Project status</option>
					<option value="NEW">New</option>
					<option value="FIN">Finished</option>
					<option value="PLA">Planned</option>
					<option value="INP">In progress</option>
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
						<td align="center"><input id="${project.getId()}"
							type="checkbox" class="checkIcon" value="${project.isNew()}"></td>
						<td align="right"><a href="project/${project.getId()}/detail"
							class="projectDetail">${project.getProjectNumber()}</a></td>
						<td>${project.getName()}</td>
						<td>${project.getStatus().getValue()}</td>
						<td>${project.getCustomer()}</td>
						<fmt:formatDate value="${project.getStartDate()}" var="dateString"
							pattern="dd.MM.yyyy" />
						<td>${dateString}</td>
						<td align="center"><a
							href="project/${project.getId()}/delete" class="deleteIcon">
								<c:choose>
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

		<div class="resultRow">
			<p class="totalItems">2 items selected</p>
			<a href="<c:url value='/deleteMultiple'/>" class="deleteMultiple">delete
				selected items <span class="glyphicon glyphicon-trash"></span>
			</a>
		</div>
	</div>
	<input type="hidden" id="paginationMax" value="${pages}">
	<c:choose>
		<c:when test="${selected%links ==0}">
			<input type="hidden" id="paginationStart" value="${selected -(links-1)}">
			<input type="hidden" id="paginationEnd" value="${selected}">
		</c:when>
		<c:otherwise>
			<input type="hidden" id="paginationStart" value="${selected}">
			<c:choose>
				<c:when test="${pages<links}">
				<input type="hidden" id="paginationEnd" value="${pages}">
				</c:when>
				<c:otherwise>
				<input type="hidden" id="paginationEnd" value="${selected + (links-1)}">
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${isSearchResult==true}">
			<c:set var="directiveClass" value="search/page/" />
		</c:when>
		<c:otherwise>
			<c:set var="directiveClass" value="projects/page/" />
		</c:otherwise>
	</c:choose>
	<ul class="pagination">
		<li><a href="${directiveClass}" class="directives"
				id="previous"><img id="logo"   src="resources/images/previous_page.png"></a></li>
		<c:forEach begin="1" end="${pages}" varStatus="loop">
			<c:choose>
				<c:when test="${selected==loop.index}">
					<c:set var="className" value="paging selected" />
				</c:when>
				<c:otherwise>
					<c:set var="className" value="paging" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${isSearchResult==true}">
					<li><a class="${className}" id="${loop.index}"
						href="search/page/${loop.index}">${loop.index}</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="${className}" id="${loop.index}"
						href="projects/page/${loop.index}">${loop.index}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li><a  href="${directiveClass}" class="directives" id="next"><img id="logo" 
				src="resources/images/nextpage_icon.png"></a></li>
	</ul>

</div>
<spring:url value="/resources/js/main.js" var="mainJS" />
<script src="${mainJS}"></script>
