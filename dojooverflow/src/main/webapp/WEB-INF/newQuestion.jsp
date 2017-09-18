<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Question</title>
</head>
<body>
<h1>New Question</h1>
<form method="POST" action="/questions/new">
    <label id="question">Question</label>
    <textarea name="question" id="question" cols="30" rows="10"></textarea>
    

    <label for="tags">Tags</label>
    <input type="text" id="tags" name="tags">

    <input type="submit" value="Submit" />
</form>
</body>
</html>