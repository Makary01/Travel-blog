<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username}'s profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>

<div id="container">
    <p>User nr ${user.id}</p>
    <p>Username: ${user.username}</p>
    <p>From: ${user.city},${user.country}</p>
    <p>Email: ${user.email}</p>

    <%--    User's trips:--%>
    <%--    <ul>--%>
    <%--        <c:forEach items="${user.trips}" var="trip">--%>
    <%--            <li><a href="/app/adventure?id=${trip.id}">${trip.title}</a>|${trip.type}</li>--%>
    <%--        </c:forEach>--%>
    <%--    </ul>--%>
</div>
</body>
</html>