<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Page</title>
</head>
<body>
<h1>${category.name}</h1>
<div class="main">
    <div class="list">
        <h3>Products:</h3>
        <ul>
            <c:forEach items="${category.products}" var="product">
            	<li>${product.name}</li>
            </c:forEach>
        </ul>
    </div>
    <form method="POST" action="/categories/${category.id}">
        <select name="productId">
            <c:forEach items="${products}" var="product">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Add" />
    </form>
</div>
</body>
</html>