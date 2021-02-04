<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<h1>Change password</h1>
<a href="/app/dashboard">Back to home page</a>
<form:form method="POST"
           modelAttribute="passwordChanger"
           action="/app/user/change-password">
    <label for="currentPassword">Current password:</label>
    <form:password path="currentPassword" />

    <label for="newPassword">New password:</label>
    <form:password path="newPassword" />

    <input type="submit" value="Save">
    <p><form:errors path="*" /></p>
</form:form>

</div>
</body>
</html>