<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username}'s profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>
<button onclick="goBack()">Go Back</button>
<script>function goBack() {window.history.back();}</script>

<div id="container">
    <p>User nr ${user.id}</p>
    <p>Username: ${user.username}</p>
    <p>From: ${user.city},${user.country}</p>
    <p>Email: ${user.email}</p>

    User's latest trips:
    <table border="1">
        <thead>
        <th>id</th>
        <th>Title</th>
        <th>Types</th>

        </thead>
        <c:forEach items="${user.trips}" var="trip">
            <tr>
                <td>
                        ${trip.id}
                </td>
                <td>
                    <a href="/app/trip/${trip.id}">${trip.title}</a>
                </td>
                <td>
                    <c:forEach items="${trip.types}" var="type">
                        ${type.name}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>