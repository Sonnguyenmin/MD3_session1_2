package com.example.qlusers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_user";
    public static final String USER = "root";
    public static final String PASS = "123456";

    public static Connection openConnection() {
        Connection con;
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
