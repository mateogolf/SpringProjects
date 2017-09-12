<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
RENDER BITCH!
<form:form method="POST" action="/books/create" modelAttribute="book">
    <form:label path="title">Title
    <form:errors path="title"/>
    <form:input path="title"/><c:out value="${book.title}"/></form:label>
    
    <form:label path="description">Description
    <form:errors path="description"/>
    <form:textarea path="description"/><c:out value="${book.description}"/></form:label>
    
    <form:label path="language">language
    <form:errors path="language"/>
    <form:input path="language"/><c:out value="${book.language}"/></form:label>
    
    <form:label path="numberOfPages">Pages
    <form:errors path="numberOfPages"/>     
    <form:input type="number" path="numberOfPages"/><c:out value="${book.numberOfPages}"/></form:label>
    
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>