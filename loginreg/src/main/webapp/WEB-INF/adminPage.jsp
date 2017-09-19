<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<header>
	<h1>Welcome ${currentUser.firstName}!</h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</header>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.email}</td>
                <td>
                    <%boolean adminFound=false; %>
                    <c:forEach items="${user.roles}" var="role">
                        <c:choose>
                        <c:when test="${role.id == 2}">
                        	<%adminFound=true; %>
                        </c:when>
                        </c:choose>
                    </c:forEach>
                    <% if(adminFound){%>
                    	<p>admin</p>
                    <%}else{ %>
                    	<p><a href="/delete/${user.id}">delete</a> <a href="/makeAdmin/${user.id}">make-admin</a></p>
                    <%} %>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>