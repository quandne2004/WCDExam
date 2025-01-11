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
    <title>Employee Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>ABC Company - Employee Management</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Employee button -->
        <input type="button" value="Add Employee" onclick="window.location.href='add-employee-form.jsp'; return false;" class="add-employee-button" />

        <table>
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Birthday</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">
                <!-- Set up a link for each employee -->
                <c:url var="updateLink" value="EmployeeControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="employeeId" value="${tempEmployee.employeeId}"/>
                </c:url>
                <!-- Set up a link to delete an employee -->
                <c:url var="deleteLink" value="EmployeeControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="employeeId" value="${tempEmployee.employeeId}"/>
                </c:url>
                <tr>
                    <td>${tempEmployee.employeeId}</td>
                    <td>${tempEmployee.employeeName}</td>
                    <td>${tempEmployee.email}</td>
                    <td>${tempEmployee.phoneNumber}</td>
                    <td>${tempEmployee.birthday}</td>
                    <td>
                        <a href="${updateLink}" class="update-link">Update</a> |
                        <a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
