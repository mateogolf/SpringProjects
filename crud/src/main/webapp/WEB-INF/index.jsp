<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Languages Dashboard</title>
</head>
<body>
	<p><c:out value="${msg}"/></p>
	<table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Creator</th>
                <th>Version</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        	<%int count = 0; %>
            <c:forEach items="${languages}" var="language">
                <tr>
                	
                    <td><a href="/languages/<%=count%>"><c:out value="${language.name}" /></a></td>
                    <td><c:out value="${language.creator}" /></td>
                    <td><c:out value="${language.version}" /></td>
                    <td><a href="/languages/delete/<%=count%>">delete</a> <a href="/languages/edit/<%=count%>">edit</a></td>
                	<%count++; %>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <form:form method="POST" action="/languages/create" modelAttribute="language">
        <form:label path="name">Name
            <form:errors path="name" />
            <form:input path="name" /></form:label>

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