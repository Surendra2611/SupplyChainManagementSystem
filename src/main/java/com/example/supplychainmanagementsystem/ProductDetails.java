package com.example.supplychainmanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
 // this should not be made here ow it will made on every press of button
 // public TableView<Product> productTable =new TableView<>();
    public TableView<Product> productTable;
   public Pane getAllProducts(){
       TableColumn id = new TableColumn<>("id");
       id.setCellValueFactory(new PropertyValueFactory<>("id"));

       TableColumn name = new TableColumn<>("Name");
       name.setCellValueFactory(new PropertyValueFactory<>("name"));

       TableColumn price = new TableColumn<>("Price");
       price.setCellValueFactory(new PropertyValueFactory<>("price"));

       final ObservableList<Product> data = FXCollections.observableArrayList();
       data.add(new Product(1,"lenovo",90000));
       data.add(new Product(2,"macbook",125000));

       productTable = new TableView<>();
       productTable.setItems(data);
       productTable.getColumns().addAll(id,name,price);

       Pane tablePane = new Pane();
       tablePane.getChildren().add(productTable);
       return tablePane;

   }

}
