<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit trip</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<form:form method="POST"
           modelAttribute="trip"
           action="/app/trip/edit/${trip.id}">
    <label for="title">Title:</label>
    <form:input path="title"/>

    <label for="types">Type(s) of trip:</label>
    <form:checkboxes items="${types}" path="types" itemLabel="name"/>

    <label for="destination">Destination:</label>
    <form:input path="destination"/>

    <label for="content">Content:</label>
    <form:textarea path="content"/>

    <label for="startDate">The beginning of the journey :</label>
    <form:input type="date" path="startDate"/>

    <label for="endDate">the end of the journey :</label>
    <form:input type="date" path="endDate"/>

    <input type="submit" value="Save">
    <p><form:errors path="*"/></p>
</form:form>

</div>
</body>
</html>