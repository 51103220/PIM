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
	<p class="formName">
		<spring:message code="menu.projectsList" />
	</p>
	<spring:message code="placeholder.search" var="searchPlaceholder" />
	<spring:message code="placeholder.status" var="searchStatus" />
	<div class="formContent">
		<form class="form-inline" action="filterProject" role="form"
			id="searchInputs">
			<div class="form-group">
				<input type="text" id="keywords" class="form-control firstInput"
					placeholder="${searchPlaceholder}" value="${searchValue}">
			</div>
			<div class="form-group">
				<select class="form-control empty" id="statusKey">
					<option value="" selected disabled>${searchStatus}</option>
					<option></option>
					<c:forEach items="${statusValues}" var="status">
						<option value="${status}"><spring:message
								code="status.${status}" /></option>
					</c:forEach>
				</select>

			</div>
			<div class="form-group">
				<button type="button" id="search_btn"
					class="btn btn-primary processBtn">
					<spring:message code="button.search" />
				</button>
			</div>
			<div class="form-group">
				<a href="#" id="reset_btn"> <spring:message
						code="link.resetSearch" /></a>
			</div>
		</form>
		<div class="table-responsive">
			<table class="table" id="searchDatas">
				<thead>
					<tr>
						<th></th>
						<th class="sorter-true"><spring:message code="table.number" /></th>
						<th class="sorter-true"><spring:message code="table.name" /></th>
						<th class="sorter-true"><spring:message code="table.status" /></th>
						<th class="sorter-true"><spring:message code="table.customer" /></th>
						<th class="sorter-true"><spring:message code="table.date" /></th>
						<th><spring:message code="table.delete" /></th>
					</tr>
					<tr id="filterInputs">
						<th></th>
						<th class="col30px"><input type="text" name="pNumber"
							class="form-control"></th>
						<th><input type="text" name="pName" class="form-control"></th>
						<th class="col30px"><input type="text" name="pStatus"
							class="form-control"></th>
						<th class="col30px"><input type="text" name="pCustomer"
							class="form-control"></th>
						<th class="col30px"><input type="text" name="pDate"
							class="form-control"></th>
						<th></th>
					</tr>

				</thead>
				<tbody>
					<c:forEach items="${projects}" var="project">
						<tr>
							<td align="center"><input id="${project.getId()}"
								type="checkbox" class="checkIcon" value="${project.isNew()}"></td>
							<td class="col1" align="right"><a
								href="project/${project.getId()}/detail" class="projectDetail">${project.getProjectNumber()}</a></td>
							<td class="col2">${project.getName()}</td>
							<td class="col3">${project.getStatus().getValue()}</td>
							<td class="col4">${project.getCustomer()}</td>
							<fmt:formatDate value="${project.getStartDate()}"
								var="dateString" pattern="dd.MM.yyyy" />
							<td class="col5">${dateString}</td>
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
		</div>
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
			<input type="hidden" id="paginationStart"
				value="${selected -(links-1)}">
			<input type="hidden" id="paginationEnd" value="${selected}">
		</c:when>
		<c:otherwise>
			<input type="hidden" id="paginationStart" value="${selected}">
			<c:choose>
				<c:when test="${pages<links}">
					<input type="hidden" id="paginationEnd" value="${pages}">
				</c:when>
				<c:otherwise>
					<input type="hidden" id="paginationEnd"
						value="${selected + (links-1)}">
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
		<li><a href="${directiveClass}" class="directives" id="previous"><img
				id="logo" src="resources/images/previous_page.png"></a></li>
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
		<li><a href="${directiveClass}" class="directives" id="next"><img
				id="logo" src="resources/images/nextpage_icon.png"></a></li>
	</ul>

</div>

<script>
	$("#projectList #searchDatas").tablesorter({
		selectorHeaders : '.sorter-true'
	});
</script>

