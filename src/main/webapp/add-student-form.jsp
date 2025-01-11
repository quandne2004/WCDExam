<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.io.IOException" %>
<%@ page import="java.io.PrintWriter, java.sql.DriverManager" %>
<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
<h1>Add New Employee</h1>
<form action="saveEmployee.jsp" method="post">
    Employee Name: <input type="text" name="employeeName" required><br>
    Email: <input type="email" name="employeeEmail" required><br>
    Phone Number: <input type="text" name="employeePhone" required><br>
    Birthday: <input type="date" name="employeeBirthday" required><br>
    <input type="submit" value="Save">
</form>
<br>
<a href="listEmployees.jsp">Back to Employee List</a>
</body>
</html>
