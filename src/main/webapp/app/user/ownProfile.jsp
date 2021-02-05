<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a>

<h1>My profile</h1>

<div id="container">
    <p>User nr ${user.id}</p>
    <p>Username: ${user.username}</p>
    <p>From: ${user.city},${user.country}</p>
    <p>Email: ${user.email}</p>

    My latest trips:
    <ul>
        <c:forEach items="${user.trips}" var="trip">
            <li><a href="/app/trip/${trip.id}">${trip.title}</a>|${trip.type}</li>
        </c:forEach>
    </ul>
</div>
<a href="/app/user/edit">Edit profile</a>
<a href="/app/user/change-password">Change password</a>
<a href="/app/user/delete-account">Delete account</a>

</body>
</html>