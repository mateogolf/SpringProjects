<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Survey Results</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<h1>Submitted Info</h1>
        <table>
            <tbody>
                <tr>
                    <td>Your Name:</td>
                    <td><c:out value="${name}"/></td>
                </tr>
                <tr>
                    <td>Dojo Location:</td>
                    <td><c:out value="${location}"/></td>
                </tr>
                <tr>
                    <td>Favorite Language:</td>
                    <td><c:out value="${favorite}"/></td>
                </tr>
                <tr>
                    <td>Comments:</td>
                    <td><c:out value="${comments}"/></td>
                </tr>
            </tbody>
        </table>
        <a href="/return">Go back</a>
</body>
</html>