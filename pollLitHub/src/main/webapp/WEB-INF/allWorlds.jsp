<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Worlds</title>
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
<h2>Worlds</h2>
<table class="table table-striped table-hover table-condensed table-responsive points">
    <thead>
	    <tr>
	        <th>Name</th>
	        <th>Description</th>
	        <th>Actions</th>
	    </tr>
    </thead>
    <tbody>
        <c:forEach items="${worlds}" var="world">
            <tr>
                <td><a href="/worlds/${world.id}">${world.name}</a></td>
                <td>${world.description}</td>
                <!--<c:choose>
                	<c:when test="">
                	</c:when>
                	<c:otherwise>
                	</c:otherwise>
                </c:choose>-->
                <td><a href="/worlds/${world.id}/follow">Follow</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</div>
</body>
</html>