<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>

<body>
<form:form method="POST"
           modelAttribute="user"
           action="/register" id="register">

    <p>Please sign up</p>

    <form:input path="username" placeholder="Username"/>

    <form:password path="password" placeholder="Password"/>

    <form:input path="email" placeholder="Email"/>

    <form:input path="city" placeholder="City"/>

    <form:input path="country" placeholder="Country"/>

    <input type="submit" value="Register" style="cursor: pointer">
    <p><form:errors path="*" /></p>
</form:form>
</div>
</body>
</html>