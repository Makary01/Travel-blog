<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<h1>Welcome ${user.username}!</h1>
<a href="/app/user">My Profile</a>
<a href="/app/trip">My trips</a>
<a href="/app/trip/add">Add trip</a>
<a href="/logout">Logout</a>
</div>
</body>
</html>