package com.jeison.araya.examples.db.example.util;


import java.sql.*;

/**
 * @author Jeison Araya Mena | B90514
 */
public class ConnectionDB {
    // Variables \\
    private static final String URL =  "jdbc:sqlserver://163.178.107.10:1433;databaseName=2020_IF-3100_B90127;";
    private static final String USER = "laboratorios";
    private static final String PASSWORD = "Saucr.118";
    // Methods \\
    /**
     * This method returns the connection with DB
     *
     * @return {@code Connection} connection with DB, {@code null} error.
     * @throws SQLException when an error happened during DB connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
