package com.example.adminservlet.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/JobHunter";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}