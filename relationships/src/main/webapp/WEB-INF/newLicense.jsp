<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New License</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<form:form method="POST" action="/licenses/create" modelAttribute="license">
    <form:label path="person">License Holder:
        <form:errors path="person" />
        <form:select path="person">
            <c:forEach items="${persons}" var="person">
                <form:option value="${person.id}">${person.firstName} ${person.lastName}</form:option>
            </c:forEach>
        </form:select>
    </form:label>
    
    <form:label path="state">State
    <form:errors path="state" />
    <form:input path="state" /></form:label>
    
    <form:label path="expiration_date">Expiration Date
    <form:errors path="expiration_date" />
    <form:input type="date" path="expiration_date" /></form:label>
    
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>