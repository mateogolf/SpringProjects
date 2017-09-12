<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ninja Gold Game</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<form action="/gold">
        <p>Your Gold: <input type="text" name="gold" value="<c:out value="${gold}"/>"></p>
    </form>
    <div class="wrapper">
        <div id="incomeSources">
            <form action="/process_money/farm" method="post">
                <h2>Farm</h2>
                <p>(earns 10-20 gold)</p>
                <input type="submit" value="Find Gold!" />
            </form>
            <form action="/process_money/cave" method="post">
                <h2>Cave</h2>
                <p>(earns 5-10 gold)</p>
                <input type="submit" value="Find Gold!" />
            </form>
            <form action="/process_money/house" method="post">
                <h2>House</h2>
                <p>(earns 2-5 gold)</p>
                <input type="submit" value="Find Gold!" />
            </form>
            <form action="/process_money/casino" method="post">
                <h2>Casino</h2>
                <p>(earns 0-50 gold)</p>
                <input type="submit" value="Find Gold!" />
            </form>
        </div>
        <p>Activities:</p>
        <div id="activities" name="activities">
            <ul>
                <!--<c:forEach var="activity" items="${activities}">
                	<li><c:out value="${activity}"/></li>
                </c:forEach>-->
                <%
                	if(session.getAttribute("activities") != null){
                		ArrayList<String> activities = (ArrayList<String>)session.getAttribute("activities");
                		for(String activity:activities){
                			if(activity.startsWith("Earned")){%>
                				<li><p style='color:green'><%=activity %></p></li>
                			<%} else{%>
                				<li><p style='color:red'><%=activity %></p></li>
                			<%}
                		}
                	}
                %>
            </ul>
        </div>
        <a href="/reset">Reset Game</a>
    </div>
</body>
</html>