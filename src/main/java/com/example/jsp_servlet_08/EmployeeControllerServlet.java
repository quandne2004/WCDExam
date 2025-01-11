package com.example.jsp_servlet_08;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmployeeDBUtil employeeDBUtil;

    @Resource(name = "jdbc/hr_manage")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            if (dataSource == null) {
                throw new ServletException("DataSource is null!");
            }
            employeeDBUtil = new EmployeeDBUtil(dataSource);  // Truyền DataSource vào
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Đọc tham số "command"
            String theCommand = request.getParameter("command");
            // Nếu thiếu tham số "command", mặc định là liệt kê nhân viên
            if (theCommand == null) theCommand = "list";

            // Chuyển hướng đến phương thức tương ứng với lệnh
            switch (theCommand) {
                case "ADD":
                    addEmployee(request, response);
                    break;
                case "LOAD":
                    loadEmployee(request, response);
                    break;
                case "UPDATE":
                    updateEmployee(request, response);
                    break;
                case "DELETE":
                    deleteEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Đọc ID nhân viên từ dữ liệu form
        String theEmployeeId = request.getParameter("employeeId");

        // Xóa nhân viên khỏi cơ sở dữ liệu
        employeeDBUtil.deleteEmployee(theEmployeeId);

        // Quay lại trang danh sách nhân viên
        listEmployees(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Đọc thông tin nhân viên từ dữ liệu form
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        String employeePhone = request.getParameter("employeePhone");
        String employeeBirthday = request.getParameter("employeeBirthday");

        // Tạo đối tượng nhân viên mới
        Employee theEmployee = new Employee(employeeId, employeeName, employeeEmail, employeePhone, employeeBirthday);

        // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
        employeeDBUtil.updateEmployee(theEmployee);

        // Quay lại trang danh sách nhân viên
        listEmployees(request, response);
    }

    private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Đọc ID nhân viên từ dữ liệu form
        String theEmployeeId = request.getParameter("employeeId");

        // Lấy thông tin nhân viên từ cơ sở dữ liệu
        Employee theEmployee = employeeDBUtil.getEmployee(theEmployeeId);

        // Đưa thông tin nhân viên vào thuộc tính request
        request.setAttribute("THE_EMPLOYEE", theEmployee);

        // Chuyển hướng đến trang cập nhật nhân viên
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Đọc thông tin nhân viên từ dữ liệu form
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        String employeePhone = request.getParameter("employeePhone");
        String employeeBirthday = request.getParameter("employeeBirthday");

        // Tạo đối tượng nhân viên mới
        Employee theEmployee = new Employee(employeeName, employeeEmail, employeePhone, employeeBirthday);

        // Thêm nhân viên vào cơ sở dữ liệu
        employeeDBUtil.addEmployee(theEmployee);

        // Quay lại trang danh sách nhân viên
        listEmployees(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Lấy danh sách nhân viên từ cơ sở dữ liệu
        List<Employee> employees = employeeDBUtil.getEmployees();

        // Đưa danh sách nhân viên vào thuộc tính request
        request.setAttribute("EMPLOYEE_LIST", employees);

        // Chuyển hướng đến trang JSP hiển thị danh sách nhân viên
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employees.jsp");
        dispatcher.forward(request, response);
    }
}
