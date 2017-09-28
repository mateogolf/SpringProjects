<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
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
        <p class="navbar-brand">Build Your Adventure!</p>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
    	<ul class="nav navbar-nav">
    		<li class="active"><a href="/profile"><span class="glyphicon glyphicon-user"></span>${currentUser.username}</a></li>
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
        	<li><a href="/worlds">Browse Worlds</a></li>
        	<li>
        		<form method="POST" action="/worlds/search"><!-- modelAttribute="search"> -->
			        <input type="text" name="search" placeholder="Search">
			        <input type="submit" value="Search">
			    </form>
        	</li>
        	<li>
            	<form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			        <input type="submit" class="btn btn-link" value="Logout" />
			    </form>
            </li>
            
        </ul>
    </div>
</nav>
<div class="profile container-fluid col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">
<table class="table">
    <tr>
        <td>Username</td>
        <td>${currentUser.username}</td>
    </tr>
    <tr>
        <td>Sign up date:</td>
        <td><fmt:formatDate pattern="MMMMM dd, yyyy" value = "${currentUser.createdAt}" /></td>
    </tr>
    <tr>
        <td>Last sign in:</td>
        <td><fmt:formatDate pattern="MMMMM dd, yyyy" value = "${currentUser.updatedAt}" /></td>
    </tr>
</table>
</div>

<div class="container-fluid col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">

<h2>My Worlds</h2>
<table class="table table-striped table-hover table-condensed table-responsive points">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${currentUser.createdWorlds}" var="world">
        <tr>
            <td><a href="/worlds/${world.id}">${world.name}</a></td>
            <td>${world.description}</td>
            <td><a href="/worlds/${world.id}/edit">Edit</a> <a href="/worlds/${world.id}/delete">Delete</a></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<h2>Followed Words</h2>
<table class="table table-striped table-hover table-condensed table-responsive points">
    <thead>
	    <tr>
	        <th>Name</th>
	        <th>Description</th>
	        <th>Clearance</th>
	        <th>Actions</th>
	    </tr>
    </thead>
    <tbody>
        <c:forEach items="${currentUser.followerRoles}" var="follower">
            <tr>
                <td><a href="/worlds/${follower.world.id}">${follower.world.name}</a></td>
                <td>${follower.world.description}</td>
                <td>${follower.clearance.name}</td>
                <td><a href="/worlds/${follower.id}/unfollow">Unfollow</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<fieldset>
    <Legend>Create World</Legend>
    <form:errors path="world.*" />
    <form:form method="POST" action="/profile" modelAttribute="world">
    <table>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="description">Description:</form:label></td>
            <td></td>
            <td><form:textarea path="description" /></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td><input type="submit" value="Create new World" /></td>
        </tr>
        </table>

    </form:form>
</fieldset>
</div>

</body>
</html>