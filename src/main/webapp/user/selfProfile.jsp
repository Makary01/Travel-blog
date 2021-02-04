<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>${user.username}'s profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/home">Back to home page</a><br>
<%--=======================--%>
<%--profile and selfProfile connection--%>
<%--=======================--%>

<div id="container">
    User nr${user.id}<br>
    Username: ${user.username}<br>
    From: ${user.city},${user.country}<br>
    Email: ${user.email}<br><br>

<%--    User's trips:--%>
<%--    <ul>--%>
<%--        <c:forEach items="${user.trips}" var="trip">--%>
<%--            <li><a href="/app/adventure?id=${trip.id}">${trip.title}</a>|${trip.type}</li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>

    <a href="/edit">edit</a>
</div>
</body>
</html>