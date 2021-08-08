package com.example.jupiter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class deleteSQL {
    // Run this as a Java application to reset the database.
    public static void main(String[] args) {
        try {

            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.getMySQLAddress());
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(MySQLDBUtil.getMySQLAddress());

            if (conn == null) {
                return;
            }
            Statement statement = conn.createStatement();//帮助执行SQL语句 可重复使用
            // Step 2 Drop tables in case they exist.
            String sql = "DROP TABLE IF EXISTS favorite_records";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS items";
            statement.executeUpdate(sql);
            conn.close();
            System.out.println("Import done successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

