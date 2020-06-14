package com.jeison.araya.examples.db.example.util;
import java.sql.*;
public class ConnectionDB {
    // Local DB
//    private static final String URL = "jdbc:mysql://localhost:3306/tienda?autoReconnet=true&useSSL=false";
//    public static final String user = "root";
//    public static final String password = "Admin.12";
    // Recinto DB
    private static final String URL = "jdbc:mysql://163.178.173.148:3306/tienda?autoReconnet=true&useSSL=false";
    public static final String user = "root";
    public static final String password = "Admin.12";
    /**
     * This method returns the connection with DB
     * @return {@code Connection} connection with DB, {@code null} error.
     * @throws ClassNotFoundException when class is not found.
     * @throws SQLException when an error happened during DB connection.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}
