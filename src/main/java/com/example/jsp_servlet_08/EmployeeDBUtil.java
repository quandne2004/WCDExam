package com.example.jsp_servlet_08;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDBUtil {

    private DataSource dataSource;

    public EmployeeDBUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    // Lấy tất cả nhân viên
    public List<Employee> getEmployees() throws Exception {

        List<Employee> employees = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL statement
            String sql = "SELECT * FROM employee ORDER BY employee_name";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {
                // retrieve data from result set
                int id = myRs.getInt("employee_id");
                String name = myRs.getString("employee_name");
                String email = myRs.getString("email");
                String phone = myRs.getString("phone_number");
                String birthday = myRs.getString("birthday");

                // create new Employee object
                Employee tempEmployee = new Employee(id, name, email, phone, birthday);

                // add it to the list
                employees.add(tempEmployee);
            }

            return employees;
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    // Thêm nhân viên mới
    public void addEmployee(Employee theEmployee) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL for insert
            String sql = "INSERT INTO employee (employee_name, email, phone_number, birthday) "
                    + "VALUES (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the employee
            myStmt.setString(1, theEmployee.getEmployeeName());
            myStmt.setString(2, theEmployee.getEmail());
            myStmt.setString(3, theEmployee.getPhoneNumber());
            myStmt.setString(4, theEmployee.getBirthday());

            // execute SQL insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    // Lấy thông tin nhân viên theo ID
    public Employee getEmployee(String theEmployeeId) throws Exception {

        Employee theEmployee = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int employeeId;

        try {
            // convert employee id to int
            employeeId = Integer.parseInt(theEmployeeId);

            // get connection to database
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL to get selected employee
            String sql = "SELECT * FROM employee WHERE employee_id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, employeeId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set
            if (myRs.next()) {
                String name = myRs.getString("employee_name");
                String email = myRs.getString("email");
                String phone = myRs.getString("phone_number");
                String birthday = myRs.getString("birthday");

                // use the employeeId during construction
                theEmployee = new Employee(employeeId, name, email, phone, birthday);
            } else {
                throw new Exception("Could not find employee id: " + employeeId);
            }

            return theEmployee;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    // Cập nhật thông tin nhân viên
    public void updateEmployee(Employee theEmployee) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL update statement
            String sql = "UPDATE employee SET employee_name=?, email=?, phone_number=?, birthday=? WHERE employee_id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theEmployee.getEmployeeName());
            myStmt.setString(2, theEmployee.getEmail());
            myStmt.setString(3, theEmployee.getPhoneNumber());
            myStmt.setString(4, theEmployee.getBirthday());
            myStmt.setInt(5, theEmployee.getEmployeeId());

            // execute SQL statement
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }



    // Xóa nhân viên
    public void deleteEmployee(String theEmployeeId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert employee id to int
            int employeeId = Integer.parseInt(theEmployeeId);

            // get connection to database
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL to delete employee
            String sql = "DELETE FROM employee WHERE employee_id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, employeeId);

            // execute SQL statement
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    // Đóng tài nguyên JDBC
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // Doesn't really close it, just puts it back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    // Tìm kiếm nhân viên theo tên hoặc email
    public List<Employee> searchEmployee(String keyword) throws Exception {

        List<Employee> employees = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Kết nối với cơ sở dữ liệu
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // Tạo câu lệnh SQL tìm kiếm
            String sql = "SELECT * FROM employee WHERE employee_name LIKE ? OR email LIKE ?";
            myStmt = myConn.prepareStatement(sql);

            // Thiết lập giá trị cho tham số tìm kiếm
            String searchKeyword = "%" + keyword + "%";
            myStmt.setString(1, searchKeyword);
            myStmt.setString(2, searchKeyword);

            // Thực thi câu lệnh và xử lý kết quả
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                int id = myRs.getInt("employee_id");
                String name = myRs.getString("employee_name");
                String email = myRs.getString("email");
                String phone = myRs.getString("phone_number");
                String birthday = myRs.getString("birthday");

                // Tạo đối tượng Employee và thêm vào danh sách
                Employee tempEmployee = new Employee(id, name, email, phone, birthday);
                employees.add(tempEmployee);
            }
            return employees;
        } finally {
            // Đóng các tài nguyên JDBC
            close(myConn, myStmt, myRs);
        }
    }

}
