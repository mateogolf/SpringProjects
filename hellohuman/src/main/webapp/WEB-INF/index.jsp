<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<header>
        <h1>Hello <c:out value="${name}"/>!</h1>
        <a href="/counter">Go Back</a>
    </header>
    <br>
    <p>Welcome to SpringBoot!</p>
</body>
</html>