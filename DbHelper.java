package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String dbUrl="jdbc:mysql://localhost:3306/konut_kiralama";
    private String  user ="root";
    private String pass ="12345";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl,user,pass);
    }

}
