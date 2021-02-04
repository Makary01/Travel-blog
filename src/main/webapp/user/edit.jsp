<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<form:form method="POST"
           modelAttribute="user"
           action="/app/user/edit">
    <label for="username">Username:</label>
    <form:input path="username" />

    <label for="email">Email:</label>
    <form:input path="email" />

    <label for="city">City:</label>
    <form:input path="city"/>

    <label for="country">Country:</label>
    <form:input path="country"/>

    <label for="password">Current Password:</label>
    <form:password path="password" />

    <input type="submit" value="Save">
    <p><form:errors path="*" /></p>
</form:form>

</div>
</body>
</html>