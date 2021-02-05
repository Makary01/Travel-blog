<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trip</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a>

<div id="container">
    <h1>${trip.title}</h1>
    <h2>Author: <a href="/app/user/${trip.user.id}">${trip.user.username}</a></h2>
    <h3>Created: ${trip.created}</h3>

    <br><br><br>

    <p>Destination: ${trip.destination}</p>
    <p>Started: ${trip.startDate}</p>
    <p>Ended: ${trip.endDate}</p>

    <p>${trip.content}</p>


</div>
<a href="/app/user/edit">Edit profile</a>
<a href="/app/user/change-password">Change password</a>
<a href="/app/user/delete-account">Delete account</a>

</body>
</html>