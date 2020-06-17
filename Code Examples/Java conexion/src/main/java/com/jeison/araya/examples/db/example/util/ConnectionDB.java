package com.jeison.araya.examples.db.example.util;
import java.sql.*;
public class ConnectionDB {
    // Local DB
//    private static final String URL = "jdbc:mysql://186.176.123.76:3306/tienda";
    private static final String URL = "jdbc:mysql://186.176.123.76:3306/tienda?autoReconnet=true&useSSL=false";
    public static final String user = "remote-user";
    public static final String password = "Remote.12";

    //jdbc:sqlserver://[163.178.107.10:1433;user=laboratorios;password=Saucr.118;
    public static final String connectionUrl =  "jdbc:sqlserver://163.178.107.10:1433;user=laboratorios;password=Saucr.118";





    /**
     * This method returns the connection with DB
     * @return {@code Connection} connection with DB, {@code null} error.
     * @throws ClassNotFoundException when class is not found.
     * @throws SQLException when an error happened during DB connection.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
//        return DriverManager.getConnection(connectionUrl);
    }



}
