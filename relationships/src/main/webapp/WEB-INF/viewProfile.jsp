<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
</head>
<body>
<h1>${person.firstName} ${person.lastName}</h1>
<table>
        <tr>
            <td>License Number</td>
            <td>${person.license.number}</td>
        </tr>
        <tr>
            <td>State</td>
            <td>${person.license.state}</td>
        </tr>
        <tr>
            <td>Expiration Date</td>
            <td>${person.license.expiration_date}</td>
        </tr>
    </table>

</body>
</html>