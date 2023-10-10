package com.example.beproject22.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    static final String URL_DB = "jdbc:mysql://localhost:3306/project22?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
//    static final String URL_DB = "jdbc:sqlserver://localhost:1433;databaseName=project22";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() {
        Connection cnt = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            cnt = DriverManager.getConnection(URL_DB, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnt;
    }
}
