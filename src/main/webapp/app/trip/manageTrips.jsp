<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My trips</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

</head>
<body>
<div class="wrapper">
<div class="header">
    <a href="/app/dashboard" class="logo">Travel blog</a>
    <div class="header-right">
        <a href="/app/dashboard">Home</a>
        <a href="/app/trip/add">Add trip</a>
        <a href="/app/trip" class="active">My trips</a>
        <a href="/app/user">Profile</a>
        <a href="/logout">Logout</a>
    </div>
</div>


<form id="sortForm">
    Sort by:<br>
    <input type="radio" id="created" name="sortBy" value="created" checked>
    <label for="created">Creation date</label><br>

    <input type="radio" id="title" name="sortBy" value="title">
    <label for="created">Title</label><br>

    <input type="radio" id="startDate" name="sortBy" value="startDate">
    <label for="created">Beginning of the journey</label><br>

    <input type="radio" id="endDate" name="sortBy" value="endDate">
    <label for="created">End of the journey</label><br>

    Order:<br>
    <input type="radio" id="descending" name="order" value="desc" checked>
    <label for="descending">Descending</label><br>
    <input type="radio" id="ascending" name="order" value="asc">
    <label for="descending">Ascending</label><br>

    Type:<br>
    <c:forEach var="type" items="${allTypes}">
        <input type="checkbox" id="${type.name}" name="types" class="types" value="${type.id}">
        <label for="${type.name}">${type.name}</label>
    </c:forEach>
    <input type="button" id="checkAll" value="Select all">
    <input type="button" id="unCheckAll" value="Unselect all">

    <input type="button" id="applyButton" value="Apply">
</form>

<table border="1">
    <thead>
    <th>id</th>
    <th>Title</th>
    <th>Types</th>
    <th>Action</th>
    </thead>
    <c:forEach items="${trips}" var="trip">
        <tr>
            <td>
                    ${trip.id}
            </td>
            <td>
                    ${trip.title}
            </td>
            <td>
                <c:forEach items="${trip.types}" var="type">
                    ${type.name}
                </c:forEach>
            </td>
            <td>
                <a href="/app/trip/${trip.id}">View</a>
                <a href="/app/trip/edit/${trip.id}">Edit</a>
                <a href="/app/trip/delete/${trip.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<div id="pagination">
    Page:
    <c:if test="${currentPage>1}">
        <button id="prevPage"> < </button>
    </c:if>

    <p id="currentPage">${currentPage} of ${totalPages}</p>

    <c:if test="${currentPage<totalPages}">
        <button id="nextPage"> > </button>
    </c:if>

</div>
    <div class="push"></div>
</div>
<div class="footer">
    <p>&copy Makary Bortnowski</p>
</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manageTrips.js"></script>

