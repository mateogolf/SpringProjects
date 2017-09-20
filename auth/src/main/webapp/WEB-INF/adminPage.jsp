<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<header>
	<h1>Welcome ${currentUser.username}!</h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</header>
<table>
    <thead>
        <tr>
            <!-- <th>Name</th> -->
            <th>Username</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <%boolean adminFound=false; %>
                <c:forEach items="${user.roles}" var="role">
                    <c:if test="${role.id == 2}">
                    	<%adminFound=true; %>
                    </c:if>
                </c:forEach>
                <% if(adminFound){%>
                	<td>admin</td>
                <%}else{ %>
                	<td><a href="/delete/${user.id}">delete</a> <a href="/makeAdmin/${user.id}">make-admin</a></td>
                <%} %>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>