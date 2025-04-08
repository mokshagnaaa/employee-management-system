package com.example;

import com.example.model.Employee;
import com.example.DatabaseConfig;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public void addEmployee(Employee employee) throws SQLException, IOException {
        String sql = "INSERT INTO employees (name, email, position, department, salary, date_hired) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getDepartment());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getDateHired());

            statement.executeUpdate();
        }
    }

    public List<Employee> searchEmployees(String term) throws SQLException, IOException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE name ILIKE ? OR id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + term + "%");
            statement.setString(2, term);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPosition(resultSet.getString("position"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDateHired(resultSet.getString("date_hired"));

                employees.add(employee);
            }
        }

        return employees;
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
