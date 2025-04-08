package com.example;


import com.example.EmployeeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            EmployeeRepository repository = new EmployeeRepository();
            repository.deleteEmployee(id);
            response.getWriter().write("Employee deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error deleting employee: " + e.getMessage());
        }
    }
}
