<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trip</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>
<button onclick="goBack()">Go Back</button>
<script>function goBack() {window.history.back();}</script>

<div id="container">
    <h1>${trip.title}</h1>
    <h2>Author: <a href="/app/user/${trip.user.id}">${trip.user.username}</a></h2>
    <h3>Created: ${trip.created}</h3>
    <br>
    <p>Destination: ${trip.destination}</p>
    <p>Started: ${trip.startDate}</p>
    <p>Ended: ${trip.endDate}</p>

    Content:
    <p>${trip.content}</p>

    <form:form method="POST"
               modelAttribute="comment"
               action="/app/comment/add/${trip.id}">

        <label for="content">Add comment:</label>
        <form:textarea path="content"/>
        <input type="submit" value="Save">

        <p><form:errors path="*"/></p>
    </form:form>

    Comments:


</div>
</body>
</html>