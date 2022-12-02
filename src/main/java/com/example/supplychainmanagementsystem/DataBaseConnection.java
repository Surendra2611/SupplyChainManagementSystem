package com.example.supplychainmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/dictionary";
    private static final String USER = "root";
    private static final String PASS = "Surendra@123";

    public Statement getStatement() {
        Statement statement = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/supply_chain", "root", "Surendra@123");
            statement = conn.createStatement();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return statement;
    }

    public ResultSet getQueryTable(String query) {
        Statement statement = this.getStatement();

        try {
            return statement.executeQuery(query);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
            return null;
        }
        public int insertData(String query){
        Statement statement =getStatement();
        try {
            return statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
        }


//    public static void main(String[] args) {
//        DataBaseConnection dbConn = new DataBaseConnection();
//
//        String query = "SELECT * FROM customer";
//        ResultSet rs= dbConn.getQueryTable(query);
//        try {
//            while (rs!=null&&rs.next()){
//                System.out.println("fetched");
//                System.out.println(rs.getInt("mobile"));
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
