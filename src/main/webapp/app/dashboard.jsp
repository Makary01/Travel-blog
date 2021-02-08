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



<form id="sortForm">
    Sort by:<br>
    <input type="radio" id="created" name="sortBy" value="created" checked>
    <label for="created">Creation date</label><br>

    <input type="radio" id="title" name="sortBy" value="created">
    <label for="created">Title</label><br>

    <input type="radio" id="startDate" name="sortBy" value="created">
    <label for="created">Beginning of the journey</label><br>

    <input type="radio" id="endDate" name="sortBy" value="created">
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
    <th>Author</th>
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
                <a href="/app/user/${trip.user.id}">${trip.user.username}</a>
            </td>
        </tr>
    </c:forEach>
</table>

<div id="pagination">
    Page:
    <c:if test="${currentPage>totalPages}">
        <button><</button>
    </c:if>
    ${currentPage}
    <c:if test="${currentPage<totalPages}">
        <button>></button>
    </c:if>
    of ${totalPages}
</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dashboard.js"></script>
