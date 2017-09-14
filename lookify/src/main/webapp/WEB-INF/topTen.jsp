<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Top 10 Songs</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<header id="top10">
	<h3>Top Ten Songs:</h3>
	<a href="/dashboard">Dashboard</a>
</header>
<div>
	<!-- Results printed here -->
	<ul>
		<c:forEach items="${results}" var="song">			<li><p>${song.rating} - <a href="/song/${song.id}">${song.title}</a> - ${song.artist}</p></li>			<!-- <li><p><c:out value="${song.rating}"/> - <a href="/song/<c:out value="${song.id}"/>"><c:out value="${song.title}"/></a> - <c:out value="${song.artist}"/></p></li>-->
		</c:forEach>
	</ul>
</div>
</body>
</html>