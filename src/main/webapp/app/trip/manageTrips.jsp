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
<script>function goBack() {
    window.history.back();
}</script>

<h1>My trips</h1>
Type:


Sort by:
<button id="sortById">id</button>
<button id="sortByCreated">Created date</button>
<button id="sortByTitle" >Title</button>
<button id="sortByStartDate" >Start date</button>
<button id="sortByEndDate">End date</button>

Order:
<button id="orderAsc">Ascending</button>
<button id="orderDesc">Descending</button>

<table>
    <c:forEach items="${trips}" var="trip">
        <tr>
            <td>
                <a href="/app/trip/${trip.id}">${trip.title}</a>
            </td>
            <td>
                ${trip.types}
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manageTrips.js"></script>

