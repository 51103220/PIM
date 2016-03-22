<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="newSupply">
	<p class="formName">New Supply</p>
	<div class="formContent general-content">
		<form class="form-horizontal general-form" role="form">
			<div class="form-group">
				<label class="control-label col-sm-3" for="name">Supply
					Name <span class="isRequired">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="name">
				</div>
			</div>
		</form>
		<div class="form-group btnGroup">
			<button type="submit" class="btn btn-primary processBtn">Create
				Supply</button>
			<button type="submit" class="btn btn-default cancelBtn">Cancel</button>
		</div>
	</div>

</div>
