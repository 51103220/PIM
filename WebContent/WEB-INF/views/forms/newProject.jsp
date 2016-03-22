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
		<form class="form-horizontal general-form" role="form">
			<div class="form-group">
				<label class="control-label col-sm-3" for="pNumber">Project
					Number <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control shortWidth" id="pNumber">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pName">Project
					Name <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" id="pName">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="customer">Customer
					<span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" id="customer">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="group">Group <span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="group">
						<option value="" selected disabled>New</option>
						<option>New</option>
						<option>Finished</option>
						<option>Planned</option>
						<option>In progress</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="members">Members

				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control longWidth" id="members">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="status">Status<span
					class="isRequired">*</span></label>
				<div class="col-sm-9">
					<select class="form-control shortWidth empty" id="status">
						<option value="" selected disabled>New</option>
						<option>New</option>
						<option>Finished</option>
						<option>Planned</option>
						<option>In progress</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="startDate">Start
					Date<span class="isRequired">*</span>
				</label>
				<div class="col-sm-4">
					<div class='input-group date shortWidth'>
						<input type='text' class="form-control datePicker" id="startDate" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
				<label class="control-label col-sm-2" for="endDate">End
					Date </label>
				<div class="col-sm-3 ">
					<div class='input-group date expand'>
						<input type='text' class="form-control datePicker" id="endDate" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
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
