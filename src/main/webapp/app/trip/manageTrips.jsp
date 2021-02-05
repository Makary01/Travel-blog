<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a>

<h1>My trips</h1>

<table>
    <c:forEach items="${trips}" var="trip">
        <td>
            <td>
        <a href="/app/trip/${trip.id}">${trip.title}</a>
    </td>
            <td>
                <a href="/app/trip/${trip.id}">View</a>
                <a href="/app/trip/edit/${trip.id}">Edit</a>
                <a href="/app/trip/delete/${trip.id}">Delete</a>
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