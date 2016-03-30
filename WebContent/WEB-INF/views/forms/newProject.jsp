<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="newProject">
	<p class="formName">${formName}&nbsp;Project</p>
	<div class="errorPanel">
		<p class="panelMessage">Please Enter Mandatory Fields (*)</p>
		<a href="#" class="closePanel"> <span
			class="glyphicon glyphicon-remove"></span>
		</a>
	</div>
	<div class="formContent general-content">
		<form class="form-horizontal general-form" role="form"
			action="${formName}Project" method="POST">
			<div class="form-group">
				<label class="control-label col-sm-3" for="pNumber">Project
					Number <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="hidden" name="id" class="form-control shortWidth"
						value="${project.getId()}"> <input type="number"
						class="form-control shortWidth" name="projectNumber" id="pNumber"
						value="${project.getProjectNumber()}">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pName">Project
					Name <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" name="name"
						id="pName" value="${project.getName()}">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="customer">Customer
					<span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" name="customer"
						id="customer" value="${project.getCustomer()}">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="group">Group <span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="group"
						name="groupId">
						<c:choose>
							<c:when test="${formName=='New'}">
								<option value="" selected disabled>GROUPS</option>
								<c:forEach items="${groups}" var="group">
									<option value="${group.key}">${group.value}</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="${project.getGroupId()}" selected disabled>${leaderVisa}</option>
								<c:forEach items="${groups}" var="group">
									<option value="${group.key}">${group.value}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</select>
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="members">Members

				</label>
				<div class="col-sm-9">
					<div class=tagsDiv>
						<ul class="tags list-inline">
							<li class="tagInput"><input type="text" name="members" class=""
								id="members" value="${project.membersToString()}" placeholder="">
								<p class="hiddenError"></p></li>
						</ul>
					</div>
					<div >
						<ul class="visaList ">
							
						</ul>
					</div>


				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="status">Status<span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="status"
						name="status">
						<c:choose>
							<c:when test="${project.getStatus()=='NEW'}">
								<option value="NEW" selected disabled>New</option>
							</c:when>
							<c:when test="${project.getStatus()=='FIN'}">
								<option value="FIN" selected disabled>Finished</option>
							</c:when>
							<c:when test="${project.getStatus()=='PLA'}">
								<option value="PLA" selected disabled>Planned</option>
							</c:when>
							<c:when test="${project.getStatus()=='INP'}">
								<option value="INP" selected disabled>In Progress</option>
							</c:when>

							<c:otherwise>
								<option value="NEW" selected>New</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${formName=='New'}">
							</c:when>
							<c:otherwise>
								<option value="NEW">New</option>
								<option value="FIN">Finished</option>
								<option value="PLA">Planned</option>
								<option value="INP">In progress</option>
							</c:otherwise>
						</c:choose>

					</select>
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="startDate">Start
					Date<span class="isRequired">*</span>
				</label>
				<div class="col-sm-4">
					<div class='input-group date shortWidth'>
						<input type='text' class="form-control datePicker" id="startDate"
							name="startDate" value="${project.getStartDate()}" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
						<p class="hiddenError"></p>
						
					</div>
					
				</div>
				<label class="control-label col-sm-2" for="endDate">End Date
				</label>
				<div class="col-sm-3 ">
					<div class='input-group date expand'>
						<input type='text' class="form-control datePicker" id="endDate"
							name="endDate" value="${project.getEndDate()}" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
						<p class="hiddenError"></p>
					</div>
					
				</div>
			</div>
		</form>
		<div class="form-group btnGroup">
			<button type="submit" class="btn btn-primary processBtn">
				<c:choose>
					<c:when test="${formName=='New'}">
						Create
					</c:when>
					<c:otherwise>
						Edit
					</c:otherwise>
				</c:choose>
				Project
			</button>
			<a href="<c:url value='/'/>"><button type="submit"
					class="btn btn-default cancelBtn">Cancel</button></a>
		</div>
	</div>

</div>
<spring:url value="/resources/js/main.js" var="mainJS" />
<script src="${mainJS}"></script>
