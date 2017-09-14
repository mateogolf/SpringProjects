<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<header>
	<span>Songs by artist: <c:out value="${search}"/></span>
	<form method="POST" action="/search"><!-- modelAttribute="search"> -->
        <input type="text" name="search" value="${search}">
        <input type="submit" value="Submit">
    </form>
	<a href="/dashboard">Dashboard</a>
</header>
<c:out value="${errors}"/>
<table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Rating</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${results}" var="song">
                <tr>
                    <td><a href="/song/${song.id}"><c:out value="${song.title}" /></a></td>
                    <td><c:out value="${song.rating}" /></td>
                    <td><a href="delete/${song.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>