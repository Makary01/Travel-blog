<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>
<button onclick="goBack()">Go Back</button>
<script>function goBack() {window.history.back();}</script>

<h1>My trips</h1>

<table>
    <c:forEach items="${trips}" var="trip">
        <td>
            <td>
        <a href="/app/trip/${trip.key}">${trip.value}</a>
    </td>
            <td>
                <a href="/app/trip/${trip.key}">View</a>
                <a href="/app/trip/edit/${trip.key}">Edit</a>
                <a href="/app/trip/delete/${trip.key}">Delete</a>
            </td>
        </tr>
        </c:forEach>
</table>


</div>
<a href="/app/user/edit">Edit profile</a>
<a href="/app/user/change-password">Change password</a>
<a href="/app/user/delete-account">Delete account</a>

</body>
</html>