<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>

</head>
<body>
<header>
	<a href="/dashboard">Dashboard</a>
</header>
<table>
        <tr>
            <td>Title</td>
            <td>${song.title}</td>
        </tr>
        <tr></tr>
        <tr>
            <td>Artist</td>
            <td>${song.artist}</td>
        </tr>
        <tr></tr>
        <tr>
            <td>Rating (1-10)</td>
            <td>${song.rating}</td>
        </tr>
        <tr></tr>
    </table>
	<a href="/delete/${song.id}">Delete</a>
</body>
</html>