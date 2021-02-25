<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>
<body>
<div class="wrapper">
<div class="header">
    <a href="/app/dashboard" class="logo">Travel blog</a>
    <div class="header-right">
        <a href="/app/dashboard">Home</a>
        <a href="/app/trip/add">Add trip</a>
        <a href="/app/trip">My trips</a>
        <a href="/app/user">Profile</a>
        <a href="/logout">Logout</a>
    </div>
</div>

<form:form method="POST"
           modelAttribute="user"
           action="/app/user/edit">
    <h1>Edit profile</h1><br>
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
    <button onclick="goBack()">Go Back</button>
    <script>function goBack() {window.history.back();}</script>
    <p><form:errors path="*" /></p>
</form:form>
<a href="/app/user/change-password">Change password</a>
    <div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>