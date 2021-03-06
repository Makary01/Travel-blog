<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
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
            <a href="/app/user" class="active">Profile</a>
            <a href="/logout">Logout</a>
        </div>
    </div>
    <div id="container">
    <div id="profileData">
        <h1>My profile</h1>
    <h2>User nr ${user.id}</h2>
    <h3>Username: ${user.username}</h3>
    <h4>From: ${user.city},${user.country}</h4>
    <h4>Email: ${user.email}</h4>
        <a href="/app/user/edit">Edit profile</a>
        <a href="/app/user/change-password">Change password</a>
        <a href="/app/user/delete-account">Delete account</a>
        <h4>My latest trips:</h4>
    </div>


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
    <div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>