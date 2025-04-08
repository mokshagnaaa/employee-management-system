package com.example;



import com.example.model.Employee;
import com.example.EmployeeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchEmployee")
public class SearchEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");
        try {
            EmployeeRepository repository = new EmployeeRepository();
            List<Employee> employees = repository.searchEmployees(term);
            response.setContentType("application/json");
            response.getWriter().write(new com.google.gson.Gson().toJson(employees));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error searching employee: " + e.getMessage());
        }
    }
}
