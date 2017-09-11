<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dojo Survey Result</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<fieldset>
        <legend>Dojo Survey</legend>
		<form action="/process" method="post">
	            <table>
	                <tbody>
	                    <tr>
	                        <td>Your Name:</td>
	                        <td>
	                            <input type="text" name="name" value="<c:out value="${name}"/>">
	                        </td>
	                        <td>
	                            <c:if test="${noName != null}">
	                                <p style="color:red"><c:out value="${noName}"/></p>
	                            </c:if>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>Dojo Location:</td>
	                        <td>
	                            <!--Select for dropdown  -->
	                            <select name="location" name="location">
	                                <option value="Seattle">Seattle</option>
	                                <option value="Washington DC">Washington DC</option>
	                                <option value="Burbank">LA - Burbank</option>
	                                <option value="San Jose">San Jose</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>Favorite Language:</td>
	                        <td>
	                            <!--Select for dropdown  -->
	                            <select name="favorite" id="fav_lang">
	                                <option value="Python">Python</option>
	                                <option value="JavaScript">JavaScript</option>
	                                <option value="Swift">Swift</option>
	                                <option value="Java">Java</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>Comments(optional)</td>
	                        <td>
	                            <c:if test="${noComments != null}">
	                                <p style="color:red"><c:out value="${noComments}"/></p>
	                            </c:if>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	            <input name="comments" id="comments" value="<c:out value="${comments}"/>">
	            <input type="submit" value="Button">
	        </form>
     </fieldset>
</body>
</html>