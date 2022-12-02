package com.example.supplychainmanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;

    Product(int id,String name,double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    //without getter setter your read those details lets make some methods
    public int getId(){
        return id.get();
    }
    public String getName(){
        return  name.get();
    }
    public double getPrice(){
        return price.get();
    }
}
