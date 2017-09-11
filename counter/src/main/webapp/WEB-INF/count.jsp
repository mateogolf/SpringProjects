<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Counter</title>
</head>
<body>
<p>You have visited <a href="/">http://localhost:8080</a> <c:out value="${counter}"/> times.</p>
<p><a href="/">Test another visit?</a></p>
<p><a href="/reset">Reset</a></p>
</body>
</html>