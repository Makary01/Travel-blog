<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel blog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

</head>
<body style="background-color: #facb88">
<div id="home" style="background-color: #ffecda">
<h1>Welcome in trip blog!</h1>
    <a href="/about">About app</a>
    <a href="/app/dashboard">Log in</a>
    <a href="/register">Register</a>
</div>
</body>
</html>