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

<form:form method="POST"
           action="/app/comment/delete/${id}">
    <label for="delete">Are you sure that you want to delete comment?</label>
    <input id="delete" type="submit" value="Delete">
    <button onclick="goBack()">Go Back</button>
    <script>function goBack() {window.history.back();}</script>
    <p><form:errors path="*"/></p>
</form:form>

</div>
</body>
</html>