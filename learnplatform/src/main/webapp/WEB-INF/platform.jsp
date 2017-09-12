<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fantron Dashboard</title>
<!-- bootstrapcdn -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="head">
        <h1>Fantron!</h1>
        <div class="links">
            <ul>
                <li><a href="/m/38/0553/0733">Setup</a></li>
                <li><a href="/m/38/0483/0345">Forms</a></li>
                <li><a href="/m/38/0553/0342">Cards</a></li>
                <li><a href="/m/26/0553/0348">Advanced</a></li>
                <li><a href="/m/26/0483/2342">Binary</a></li>
            </ul>
        </div>
    </div>
    
    <div class="container-fluid col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-12">
        <p><c:out value="${content}"/></p>
    </div>
</body>
</html>