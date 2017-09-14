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
		<c:forEach items="${results}" var="song">
		</c:forEach>
	</ul>
</div>
</body>
</html>