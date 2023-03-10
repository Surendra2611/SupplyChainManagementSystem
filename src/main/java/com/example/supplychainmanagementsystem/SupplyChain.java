package com.example.supplychainmanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {

    public static final int height = 600,width = 700,upperLine=50;

    Pane bodyPane = new Pane();//another control space for body of the homepage

    public Login login = new Login();//object of Login class
    Button orderButton;
    ProductDetails productDetails = new ProductDetails();
    boolean loggedIn = false;
    Label loginLabel;

    private GridPane headerBar(){
       GridPane gridPane = new GridPane();
       gridPane.setPrefSize(width,upperLine-5);
       gridPane.setAlignment(Pos.CENTER);
       gridPane.setHgap(5);

       TextField searchText = new TextField();
       searchText.setMinWidth(250);
       searchText.setPromptText("Please search here");

          loginLabel = new Label("Please login!");
       Button loginButton = new Button("Login");

       loginButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if(loggedIn==false) {
                   bodyPane.getChildren().clear();
                   bodyPane.getChildren().add(loginPage());
                  // loggedIn = true;
               }
           }
       });

       Button searchButton = new Button("Search");
       searchButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
             //  productDetails.getAllProducts();
               bodyPane.getChildren().clear();
             //  bodyPane.getChildren().add(productDetails.getAllProducts());
               String search = searchText.getText();
               bodyPane.getChildren().add(productDetails.getProductsByName(search));
           }
       });
       gridPane.add(searchText,0,0);
       gridPane.add(searchButton,1,0);
       gridPane.add(loginLabel,2,0);
       gridPane.add(loginButton,3,0);
     //  gridPane.getChildren().remove(3,0);



       return gridPane;
    }
    private GridPane footBar(){
        GridPane gridPane = new GridPane();
        orderButton = new Button("Buy Now");

        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                productDetails.getSelectedProduct();
                if(loggedIn==false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
                else {
                    Product product = productDetails.getSelectedProduct();
                            if(product!=null){
                                String email =loginLabel.getText();
                                email=email.substring(10);
                                System.out.println(email);
                                if(Order. placeSinglerOrder(product,email)){
                                    System.out.println("Order placed");
                                }
                                else {
                                    System.out.println("Order Failed");
                                }
                            }
                            else {
                                System.out.println("Please select a product");
                            }
                }
            }
        });
        gridPane.add(orderButton,0,0);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height+80);
        return gridPane;
    }
       private GridPane loginPage(){
           Label emailLabel = new Label("Email");
           Label passwordLabel = new Label("Password");
           Label messageLabel = new Label("I am message");

           TextField emailTextfield = new TextField();
           emailTextfield.setPromptText("Please enter your email");
           PasswordField passwordField = new PasswordField();
           passwordField.setPromptText("Please enter your password ");

           Button loginButton = new Button("Login");
           loginButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                   String email = emailTextfield.getText();
                   String password = passwordField.getText();
                   if(login.customerLogin(email,password)){
                       loginLabel.setText("Welcome :"+email);
                       bodyPane.getChildren().clear();
                       bodyPane.getChildren().add(productDetails.getAllProducts());
                       loggedIn=true;

                     //  messageLabel.setText("Login Success");
                     //  messageLabel.setTextFill(Color.GREEN);
                   }
                   else {
                       messageLabel.setText("Invalid User");
                       messageLabel.setTextFill(Color.RED);
                   }
               }
           });

           //gridpane is also a type of control space in which controls can be put on grids by rows and columns like(0,5)
           GridPane gridPane = new GridPane();
           gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        //   gridPane.setAlignment(Pos.CENTER);

          // gridPane.setStyle("-fx-background-color:#C0C0C0;");

           // for gapping between controls
           gridPane.setVgap(10);
           gridPane.setHgap(10);


           gridPane.add(emailLabel,0,0);
           gridPane.add(emailTextfield,1,0);
           gridPane.add(passwordLabel,0,1);
           gridPane.add(passwordField,1,1);
           gridPane.add(loginButton,0,3);
           gridPane.add(messageLabel,1,3);
           return gridPane;
       }
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+upperLine);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setMinSize(width,height);

      //  bodyPane.setStyle("-fx-background-color:#C0C9C0;");

        bodyPane.getChildren().addAll(productDetails.getAllProducts());

        root.getChildren().addAll(
                headerBar(),
                bodyPane,
                footBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("AtYourDoor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}