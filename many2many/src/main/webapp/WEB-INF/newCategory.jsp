<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Category</title>
</head>
<body>
<h1>New Category</h1>
<form:form method="POST" action="/categories/new" modelAttribute="category">
        <form:label path="name">name
            <form:errors path="name" />
            <form:input path="name" />
        </form:label>

        <input type="submit" value="Create" />
    </form:form>
</body>
</html>