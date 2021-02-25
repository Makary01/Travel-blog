<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create trip</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>
<body>
<div class="wrapper">
<div class="header">
    <a href="/app/dashboard" class="logo">Travel blog</a>
    <div class="header-right">
        <a href="/app/dashboard">Home</a>
        <a href="/app/trip/add" class="active">Add trip</a>
        <a href="/app/trip">My trips</a>
        <a href="/app/user">Profile</a>
        <a href="/logout">Logout</a>
    </div>
</div>
<form:form method="POST"
           modelAttribute="trip"
           action="/app/trip/add">
    <h1>Create new trip</h1><br>

    <label for="title">Title:</label>
    <form:input path="title"/><br>

    <label for="types">Type(s) of trip:</label>
    <form:checkboxes items="${types}" path="types" itemLabel="name"/><br>

    <label for="destination">Destination:</label>
    <form:input path="destination"/><br>

    <label for="content">Content:</label><br>
    <form:textarea path="content"/><br>

    <label for="startDate">The beginning of the journey :</label>
    <form:input type="date" path="startDate"/><br>

    <label for="endDate">the end of the journey :</label>
    <form:input type="date" path="endDate"/><br>

    <input type="submit" value="Create"><br>

    <button onclick="goBack()">Go Back</button>
    <script>function goBack() {window.history.back();}</script>
    <p><form:errors path="*"/></p>
</form:form>


<div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>