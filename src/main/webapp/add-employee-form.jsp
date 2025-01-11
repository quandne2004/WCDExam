<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.io.IOException" %>
<%@ page import="java.io.PrintWriter, java.sql.DriverManager" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add New Employee</title>
    <style>
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Add New Employee</h1>

<!-- Form thêm nhân viên -->
<form action="saveEmployee.jsp" method="post">

    <!-- Nhập tên nhân viên -->
    Employee Name:
    <input type="text" name="employeeName" required
           pattern="^[A-Za-z\s]{3,50}$"
           title="Employee name must be between 3 and 50 alphabetic characters.">
    <br>

    <!-- Nhập email -->
    Email:
    <input type="email" name="employeeEmail" required>
    <br>

    <!-- Nhập số điện thoại -->
    Phone Number:
    <input type="text" name="employeePhone" required
           pattern="^[0-9]{10}$"
           title="Phone number must be exactly 10 digits.">
    <br>

    <!-- Nhập ngày sinh -->
    Birthday:
    <input type="date" name="employeeBirthday" required>
    <br>

    <input type="submit" value="Save">
</form>

<!-- Link quay về danh sách nhân viên -->
<br>
<a href="listEmployees.jsp">Back to Employee List</a>

</body>
</html>
