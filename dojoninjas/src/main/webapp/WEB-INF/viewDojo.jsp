<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dojo Page</title>
</head>
<body>
<h1>${dojo.name} Location Ninjas</h1>
<table>
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${dojo.ninjas}" var="ninja">
            <tr>
                <td>${ninja.firstName}</td>
                <td>${ninja.lastName}</td>
                <td>${ninja.age}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<table class="table">
    <thead>
        <th>Dojo Name</th>
        <th>Ninja First Name</th>
        <th>Ninja Last Name</th>
    </thead>
    <tbody>
        <c:forEach var="row" items="${table}">                
        <tr>
            <td>${row[0].name}</td>
            <td>${row[1].firstName}</td>
            <td>${row[1].lastName}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<table>
    <thead>
        <th>Dojo Name</th>
    </thead>
    <tbody>
        <c:forEach var="dojo" items="${dojos}">                
        <tr>
            <td>${dojo.name}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>