<%@ page language="java" contentType="text/html; charset=charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
	
<spring:url value="/resources/css/boostrap.css" var="bootCSS" />
<spring:url value="/resources/css/main.css" var="mainCSS" />
<spring:url value="/resources/css/footer.css" var="footerCSS" />
<spring:url value="/resources/css/header.css" var="headerCSS" />
<spring:url value="/resources/css/body.css" var="bodyCSS" />
<spring:url value="/resources/js/jquery-1.12.0.min.js" var="jquery" />
<spring:url value="/resources/js/main.js" var="mainJS" />
<spring:url value="/resources/js/boostrap.js" var="bootJS" />
<link href="${bootCSS}" rel="stylesheet" />
<link href="${mainCSS}" rel="stylesheet" />
<link href="${headerCSS}" rel="stylesheet" />
<link href="${footerCSS}" rel="stylesheet" />
<link href="${bodyCSS}" rel="stylesheet" />

<title><spring:message code="application.tittle" /></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<jsp:include page="main.jsp" />
	</div>
	<jsp:include page="footer.jsp" />
</body>
<script src="${jquery}"></script>
<script src="${mainJS}"></script>
<script src="${bootJS}"></script>
</html>