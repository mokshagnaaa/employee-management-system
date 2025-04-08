package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            properties.load(input);
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = loadProperties();
        if (properties == null) {
            throw new SQLException("Unable to load database properties");
        }
        String url = properties.getProperty("jdbc:postgresql://localhost:5432/EmployeeManagementSystem");
        String user = properties.getProperty("postgres");
        String password = properties.getProperty("2004");
        String driver = properties.getProperty("org.postgresql.Driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}
