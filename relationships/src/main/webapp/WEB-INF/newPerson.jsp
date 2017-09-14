<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Person</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<h1>New Person</h1>
<form:form method="POST" action="/persons/create" modelAttribute="person">
    <form:label path="firstName">First Name
    <form:errors path="firstName" />
    <form:input path="firstName" /></form:label>

	<form:label path="lastName">Last Name
	<form:errors path="lastName" />
    <form:input path="lastName" /></form:label>

	<input type="submit" value="Submit" /></td>
</form:form>
</body>
</html>