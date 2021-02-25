<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete trip</title>
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
           action="/app/trip/delete/${id}">
    <label for="submit">Do you want to delete your trip titled "${title}"?</label>
    <input type="submit" id="submit" value="Delete trip">
    <button onclick="goBack()">Go Back</button>
    <script>function goBack() {window.history.back();}</script>
</form:form>


<div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>