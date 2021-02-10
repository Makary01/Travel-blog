<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit trip</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>
<button onclick="goBack()">Go Back</button>
<script>function goBack() {window.history.back();}</script>

<form:form method="POST"
           modelAttribute="comment"
           action="/app/comment/edit/${comment.id}">

    <label for="content">Edit comment:</label>
    <form:textarea path="content"/>
    <input type="submit" value="Save">

    <p><form:errors path="*"/></p>
</form:form>

</div>
</body>
</html>