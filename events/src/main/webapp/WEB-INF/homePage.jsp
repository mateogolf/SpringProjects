<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
	<div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
        <p class="navbar-brand">Welcome ${currentUser.firstName}!</p>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
    	<ul class="nav navbar-nav">
    		<li class="active"><a href="">Home</a></li>
    		<c:if test="${isAdmin}">
            <!-- <li class="dropdown">
            	<a href="#" data-toggle="dropdown" class="dropdown-toggle">Admin <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li class="divider"></li> -->
                    <li><a href="/admin">Admin</a></li>
                <!-- </ul>
            </li>-->
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
        	<li>
            	<form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			        <input type="submit" class="btn btn-link" value="Logout" />
			    </form>
            </li>
            
        </ul>
    </div>
</nav>
<div class="container-fluid col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">
<p>Here are some of the events in your state:</p>
${localEvents}
<table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Location</th>
            <th>Host</th>
            <th>Actions/Status</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <c:forEach items="${localEvents}" var="event">
                <td><a href="/events/${event.id}">${event.name}</a></td>
                <td><fmt:formatDate pattern="MMMMM dd, yyyy" value="${event.createdAt}" /></td>
                <td>${event.location}</td>
                <td>${event.host.firstName}</td>
                <c:choose>
                   <c:when test="${event.isHost(currentUser)}">
                       <td><a href="/events/${event.id}/edit">Edit</a> <a href="/events/${user.id}/delete">Delete</a></td>
                   </c:when>
                   <c:when test="${event.hasUser(currentUser)}">
                       <td></td>
                   </c:when>
                   <c:otherwise>
                       <td><a href="/events/${event.id}/addUser/${currentUser.id}">Join</a></td>
                   </c:otherwise>
               </c:choose>
            </c:forEach>
        </tr>
    </tbody>
</table>
<p>Here are some of the events in other states:</p>
${otherEvents}
<table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Location</th>
            <th>Host</th>
            <th>Actions/Status</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <c:forEach items="${otherEvents}" var="event">
                <td><a href="/events/${event.id}">${event.name}</a></td>
                <td><fmt:formatDate pattern="MMMMM dd, yyyy" value="${event.createdAt}" /></td>
                <td>${event.location}</td>
                <td>${event.host.firstName}</td>
                <c:choose>
                   <c:when test="${event.isHost(currentUser)}">
                       <td><a href="/events/${event.id}/edit">Edit</a> <a href="/events/${user.id}/delete">Delete</a></td>
                   </c:when>
                   <c:when test="${event.hasUser(currentUser)}">
                       <td></td>
                   </c:when>
                   <c:otherwise>
                       <td><a href="/events/${event.id}/addUser/${currentUser.id}">Join</a></td>
                   </c:otherwise>
               </c:choose>
            </c:forEach>
        </tr>
    </tbody>
</table>

<form:errors path="event.*" />

    <form:form method="POST" action="/events" modelAttribute="event">
        <h2>Create an Event</h2>
        <table>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="date">Date of Event:</form:label></td>
            <td></td>
            <td><form:input type="date" path="date" /></td>
        </tr>
        <tr>
            <td><form:label path="location">Location:</form:label></td>
            <td></td>
            <td>
                <form:input path="location"/>
                <form:select path="state">
                    <c:forEach items="${stateList}" var="state">
                        <form:option value="${state}">${state}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:hidden path="host" value="${currentUser.id}" /></td>
            <td></td>
            <td><input type="submit" value="Register!" /></td>
        </tr>
        </table>
    </form:form>

</div>
</body>
</html>