<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username}'s profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/home">Back to home page</a><br>
<div id="container">
    User nr${user.id}<br>
    Username: ${user.username}<br>
    From: ${user.city},${user.country}<br>
    Email: ${user.email}<br><br>

    User's trips:
    <ul>
        <c:forEach items="${user.trips}" var="trip">
            <li><a href="/app/adventure?id=${trip.id}">${trip.title}</a>|${trip.type}</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>