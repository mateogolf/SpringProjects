<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Profile</title>
</head>
<body>
<header>
    <h1>${question.question}</h1>
    <h3>Tags: 
        <c:forEach items="${question.tags}" var="tag">
            <span>${tag.subject}    </span>
        </c:forEach>
    </h3>
</header>
<div class="main">
    <div>
        <table>
            <thead>
                <tr>
                    <th>Answers</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${question.answers}" var="answer">
                <tr>
                    <td>${answer.answer}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!--<form:form method="POST" action="/questions/${question.id}" modelAttribute="answer">
        <h3>Add your answer: </h3>
        <form:label path="answer">answer
            <form:errors path="answer" />
            <form:textarea path="answer" />
        </form:label>
        <form:input type="hidden" path="question" value="${question.id}"/>
        <input type="submit" value="Submit" />
    </form:form>-->
    <form method="POST" action="/questions/${question.id}">
        <h3>Add your answer: </h3>
        <label for="answer">Answer
            <textarea name="answer" id="answer"></textarea>
        </label>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>