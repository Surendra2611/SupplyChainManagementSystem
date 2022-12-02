package com.example.supplychainmanagementsystem;
import javafx.scene.chart.PieChart;

public class Order {
    public static boolean placeSinglerOrder(Product product, String customerEmail) {
        String orderQuery = String.format("INSERT INTO orders(quantity,customer_id,product_id,status) VALUES(1,(SELECT cid FROM WHERE email = '%s'),%s,'ORDERED')",
                customerEmail, product.getId()
        );
        DataBaseConnection dbConn = new DataBaseConnection();
        if (dbConn.insertData(orderQuery) >= 1) {
            return true;
        }
        System.out.println(orderQuery);
        return false;
    }
}

