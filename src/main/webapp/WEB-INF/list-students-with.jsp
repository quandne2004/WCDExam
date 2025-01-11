<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 6/25/2024
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- <link type="text/css" rel="stylesheet" href="css/list.css"> --%>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>CodeLean Academy</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Student button -->
        <input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button" />

        <table>
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
            </tr>
            <c:forEach var="temptCar" items="${CAR_LIST}">
                <!-- Set up a link for each student -->
                <c:url var="tempLink" value="CarControllerServerlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="carId" value="${temptCar.id}"/>
                </c:url>
                <!-- Set up a link to delete a student -->
                <c:url var="deleteLink" value="CarControllerServerlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${temptCar.id}"/>
                </c:url>
                <tr>
                    <td>${temptCar.brand}</td>
                    <td>${temptCar.model}</td>
                    <td>${temptCar.year}</td>
                    <td>
                        <a href="${tempLink}" class="update-link">Update</a> |
                        <a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>a
        </table>
    </div>
</div>
</body>
</html>
