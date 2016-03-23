<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="newProject">
	<p class="formName">New Project</p>
	<div class ="errorPanel">
		<p class="panelMessage">
			Please Enter Mandatory Fields (*)
		</p>
		<a href="#" class="closePanel">
          <span class="glyphicon glyphicon-remove"></span>
        </a>
	</div>
	<div class="formContent general-content">
		<form class="form-horizontal general-form" role="form" action="newProject" method="POST">
			<div class="form-group">
				<label class="control-label col-sm-3" for="pNumber">Project
					Number <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control shortWidth" name="project_number" id="pNumber">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pName">Project
					Name <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" name="name" id="pName">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="customer">Customer
					<span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" name="customer" id="customer">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="group">Group <span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="group" name="group">
						<option value="" selected disabled>New</option>
						<option value="1">New</option>
						<option value="2">Finished</option>
						<option value="3">Planned</option>
						<option value="4">In progress</option>
					</select>
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="members">Members

				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" name="member" id="members">
					<p class="hiddenError"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="status">Status<span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="status" name="status">
						<option value="" selected disabled>New</option>
						<option value="NEW">New</option>
						<option value="FIN">Finished</option>
						<option value="PLA">Planned</option>
						<option value="INP">In progress</option>
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
						<input type='text' class="form-control datePicker" id="startDate" name="start_date"/>
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
					<p class="hiddenError"></p>
				</div>
				<label class="control-label col-sm-2" for="endDate">End
					Date </label>
				<div class="col-sm-3 ">
					<div class='input-group date expand'>
						<input type='text' class="form-control datePicker" id="endDate" name="end_date" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
					<p class="hiddenError"></p>
				</div>
			</div>
		</form>
		<div class="form-group btnGroup">
			<button type="submit" class="btn btn-primary processBtn">Create
				Project</button>
			<button type="submit" class="btn btn-default cancelBtn">Cancel</button>
		</div>
	</div>

</div>
<spring:url value="/resources/js/main.js" var="mainJS" />
<script src="${mainJS}"></script>
