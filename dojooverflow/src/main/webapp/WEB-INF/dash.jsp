<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions Dashboard</title>
</head>
<body>
<h1>Questions Dashboard</h1>
<table>
    <thead>
        <tr>
            <th>Question</th>
            <th>Tags</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${questions}" var="question">
            <tr>
                <td><a href="/questions/${question.id}">${question.question}</a></td>
                <td>
	                <p>
						<c:forEach items="${question.tags}" var="tag">
	                    <span>${tag.subject}, </span>
	                	</c:forEach>
	                </p>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<footer>
    <a href="/questions/new">New Question</a>
</footer>
</body>
</html>