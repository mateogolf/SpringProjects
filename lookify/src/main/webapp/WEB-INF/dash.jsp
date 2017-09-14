<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lookify!</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
	<header>
	<div>
		<a href="/song/new">Add New</a>
	    <a href="/top10">Top Songs</a>
    </div>
    <form method="POST" action="/search"><!-- modelAttribute="search"> -->
        <input type="text" name="search" placeholder="Search">
        <input type="submit" value="Submit">
    </form>
	</header>
	<table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Rating</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${songs}" var="song">
                <tr>
                    <td><a href="/song/${song.id}">${song.title}</a></td>
                    <td>${song.rating}</td>
                    <td><a href="delete/${song.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	
</body>
</html>