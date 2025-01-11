package com.example.jsp_servlet_08;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String email;
    private String phoneNumber;
    private String birthday;

    // Constructor, getters, setters
    public Employee() {}

    public Employee(int employeeId, String employeeName, String email, String phoneNumber, String birthday) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public Employee(String employeeName, String employeeEmail, String employeePhone, String employeeBirthday) {
    }

    // Getters and setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
}

