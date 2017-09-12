<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="style.css"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fantron Dashboard</title>
<!-- bootstrapcdn -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>

</head>
<body class="main">
	<form action="/process" method="post">
		<p style="color:red"><c:out value="${errors}"/></p>
        <label for="password">What is the code?</label>
        <input type="password" id="password" name="password">
        <input type="submit" value="Try Code">
    </form>
</body>
</html>