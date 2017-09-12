<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit <c:out value="${language.name}" /></title>
</head>
<body>
	<div>
		<a href="/languages/delete/<c:out value="${id}" />">Delete</a>
		<a href="/languages">Dashboard</a>
	</div>
	<form:form method="POST" action="/languages/edit/${id}" modelAttribute="language">
        <form:label path="name">Name
            <form:errors path="name" />
            <form:input path="name" />
        </form:label>

        <form:label path="creator">Creator
            <form:errors path="creator" />
            <form:input path="creator" />
        </form:label>

        <form:label path="version">Version
            <form:errors path="version" />
            <form:input path="version" />
        </form:label>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>