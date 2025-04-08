package com.example;

import com.example.model.Employee;
import com.example.EmployeeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    private EmployeeRepository employeeRepository;

    @Override
    public void init() throws ServletException {
        // Initialize the EmployeeRepository instance
        employeeRepository = new EmployeeRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String dateHired = request.getParameter("dateHired");

        // Create an Employee object
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPosition(position);
        employee.setDepartment(department);
        employee.setSalary(salary);
        employee.setDateHired(dateHired);

        try {
            // Add employee to the database
            employeeRepository.addEmployee(employee);
            response.getWriter().write("Employee added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error adding employee: " + e.getMessage());
        }
    }
}
