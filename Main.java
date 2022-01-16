package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        try{
            Login login = new Login();
            login.login();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {

        }
    }
}
