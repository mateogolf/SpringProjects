<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table">
<%int count = 0; %>
<c:forEach items="${books}" var="book">
    <tr>    
    <td><c:out value="${book.title}"/></td>
    <td><c:out value="${book.description}"/></td>
    <td><c:out value="${book.language}"/></td>
    <td><c:out value="${book.numberOfPages}"/></td>
    <td><a href="/books/delete/<%=count %>">Delete</a></td>
    </tr>
    <%count++; %>
</c:forEach>
</table>
</body>
</html>