<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete account</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<h1>Are you sure that you want to delete your account permanently?</h1>
<a href="/app/dashboard">Back to home page</a>
<form:form method="POST"
           action="/app/user/delete-account">
    <input type="submit" value="Delete account">
</form:form>

</div>
</body>
</html>