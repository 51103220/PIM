<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
				<tr>
					<td align="center"><input type="checkbox" class="checkIcon"
						value=""></td>
					<td align="right">3116</td>
					<td>Dedorewan a very long line</td>
					<td>New</td>
					<td>Dedorewan</td>
					<td>25.02.2004</td>
					<td align="center"><a href="#" class="deleteIcon"> <span
							class="glyphicon glyphicon-trash"></span>
					</a></td>
				</tr>
				<tr>
					<td align="center"><input type="checkbox" class="checkIcon"
						value=""></td>
					<td align="right">3116</td>
					<td>Dedorewan</td>
					<td>New</td>
					<td>Dedorewan</td>
					<td>25.02.2004</td>
					<td align="center"><a href="#" class="deleteIcon"> <span
							class="glyphicon glyphicon-trash"></span>
					</a></td>
				</tr>
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
