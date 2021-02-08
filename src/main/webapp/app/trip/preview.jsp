<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


</div>
<a href="/app/user/edit">Edit profile</a>
<a href="/app/user/change-password">Change password</a>
<a href="/app/user/delete-account">Delete account</a>

</body>
</html>