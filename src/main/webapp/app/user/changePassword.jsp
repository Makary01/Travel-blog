<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
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
           modelAttribute="passwordChanger"
           action="/app/user/change-password">
    <h1>Change password</h1><br>

    <label for="currentPassword">Current password:</label>
    <form:password path="currentPassword" />

    <label for="newPassword">New password:</label>
    <form:password path="newPassword" />

    <input type="submit" value="Save">
    <button onclick="goBack()">Go Back</button>
    <script>function goBack() {window.history.back();}</script>
    <p><form:errors path="*" /></p>
</form:form>


<div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>