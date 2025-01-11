<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.io.IOException" %>
<%@ page import="java.io.PrintWriter, java.sql.DriverManager" %>
<%
    // Lấy các tham số từ request
    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
    String employeeName = request.getParameter("employeeName");
    String employeeEmail = request.getParameter("employeeEmail");
    String employeePhone = request.getParameter("employeePhone");
    String employeeBirthday = request.getParameter("employeeBirthday");

    // Khai báo các đối tượng kết nối cơ sở dữ liệu
    Connection conn = null;
    PreparedStatement pstmt = null;
    PrintWriter responseWriter = response.getWriter();

    try {
        // Tải Driver và kết nối đến cơ sở dữ liệu
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_manage", "root", "123456789");

        // Câu lệnh SQL để cập nhật thông tin nhân viên
        String sql = "UPDATE Employee SET employee_name = ?, employee_email = ?, employee_phone = ?, employee_birthday = ? WHERE employee_id = ?";
        pstmt = conn.prepareStatement(sql);

        // Thiết lập các tham số trong PreparedStatement
        pstmt.setString(1, employeeName);
        pstmt.setString(2, employeeEmail);
        pstmt.setString(3, employeePhone);
        pstmt.setString(4, employeeBirthday);
        pstmt.setInt(5, employeeId);

        // Thực thi câu lệnh cập nhật
        pstmt.executeUpdate();

        // Quay lại trang danh sách nhân viên
        response.sendRedirect("listEmployees.jsp");
    } catch (Exception e) {
        responseWriter.println(e.getMessage());
    } finally {
        // Đảm bảo kết nối được đóng
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                responseWriter.println(e.getMessage());
            }
        }
    }
%>
