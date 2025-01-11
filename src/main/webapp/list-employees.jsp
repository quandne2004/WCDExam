<%@ page import="com.example.jsp_servlet_08.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%
    // Get the employees from the request object (sent by servlet)
    List<Employee> employeeList = (List<Employee>) request.getAttribute("EMPLOYEE_LIST");
%>
<body>
<div id="wrapper">
    <div id="header">
        <h2>ABC Company Employee Management</h2>
    </div>
</div>
<div id="search-form">
    <form action="EmployeeControllerServlet" method="get">
        <input type="text" name="keyword" placeholder="Search by name or email" required>
        <input type="hidden" name="command" value="SEARCH">
        <input type="submit" value="Search">
    </form>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Employee ID</th>
                <th>Employee Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Birthday</th>
            </tr>
            <% for (Employee employee : employeeList) { %>
            <tr>
                <td> <%= employee.getEmployeeId() %> </td>
                <td> <%= employee.getEmployeeName() %> </td>
                <td> <%= employee.getEmail() %> </td>
                <td> <%= employee.getPhoneNumber() %> </td>
                <td> <%= employee.getBirthday() %> </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
