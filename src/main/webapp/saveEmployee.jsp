<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.DriverManager" %>

<%
    // Đọc dữ liệu từ form
    String employeeName = request.getParameter("employeeName");
    String employeeEmail = request.getParameter("employeeEmail");
    String employeePhone = request.getParameter("employeePhone");
    String employeeBirthday = request.getParameter("employeeBirthday");

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        // Kết nối đến cơ sở dữ liệu
        String url = "jdbc:mysql://localhost:3306/hr_manage";
        String username = "root";
        String password = "123456789";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);

        // Tạo câu lệnh SQL để chèn nhân viên mới
        String sql = "INSERT INTO employee (employee_name, email, phone_number, birthday) VALUES (?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        // Thiết lập các tham số
        stmt.setString(1, employeeName);
        stmt.setString(2, employeeEmail);
        stmt.setString(3, employeePhone);
        stmt.setString(4, employeeBirthday);

        // Thực thi câu lệnh SQL
        stmt.executeUpdate();

        // Chuyển hướng về danh sách nhân viên sau khi thêm thành công
        response.sendRedirect("listEmployees.jsp");

    } catch (Exception e) {
        // Hiển thị thông báo lỗi nếu xảy ra lỗi
%>
<p class="error-message">Error: <%= e.getMessage() %></p>
<a href="add-employee-form.jsp">Go back to Add Employee Form</a>
<%
    } finally {
        // Đóng kết nối và PreparedStatement
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>
