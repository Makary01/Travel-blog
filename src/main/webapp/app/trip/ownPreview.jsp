<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Trip</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/dashboard">Back to home page</a><br>
<button onclick="goBack()">Go Back</button>
<script>function goBack() {window.history.back();}</script>

<div id="container">
    <h1>${trip.title}</h1>
    <h2>Author: <a href="/app/user/${trip.user.id}">Me</a></h2>
    <h3>Created: ${trip.created}</h3>
    <br>
    <p>Destination: ${trip.destination}</p>
    <p>Started: ${trip.startDate}</p>
    <p>Ended: ${trip.endDate}</p>

    Content:
    <p>${trip.content}</p>

    <a href="/app/trip/edit/${trip.id}">Edit trip</a>
    <a href="/app/trip/delete/${trip.id}">Delete trip</a>

    <form:form method="POST"
               modelAttribute="comment"
               action="/app/comment/add/${trip.id}">

        <label for="content">Add comment:</label>
        <form:textarea path="content"/>
        <input type="submit" value="Save">

        <p><form:errors path="*"/></p>
    </form:form>

    Comments:
    <c:forEach items="${comments}" var="comment">
        <div class="comment">
            <h5>${comment.user.username}</h5>
            <h6>${comment.created}</h6>
            <p>${comment.content}</p>
            <c:if test="${comment.user.id == userId}">
                <a href="/app/comment/edit/${comment.id}">edit</a>
                <a href="/app/comment/delete/${comment.id}">delete</a>
            </c:if>
        </div>
    </c:forEach>

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

</div>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/preview.js"></script>