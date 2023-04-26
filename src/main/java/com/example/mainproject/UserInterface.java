package com.example.mainproject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserInterface {
    GridPane loginPage;
    GridPane signupPage;
    HBox headerBar,headerBar1;

    HBox footerBar;

    Button signInButton,orderButton;
    Button profileButton;
    Button signOutButton;
    Button signUpButton=new Button();
    Button placeOrderButton = new Button("Place Order");
    Label welcomeLabel;

    VBox body ;

    ProductList productList = new ProductList();
    VBox productPage;

    Customer loggedInCustomer;
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();

    public UserInterface(){
        createLoginPage();
        createHeaderBar();
        createSignupPage();
        createFooterBar();
    }
    BorderPane createContent(){
        BorderPane root = new BorderPane();
//        root.setCenter(loginPage);
        productPage = productList.getAllProducts();
        body = new VBox();
        welcomeLabel = new Label();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        body.getChildren().add(productPage);
        root.setCenter(body);
        root.setTop(headerBar);
        headerBar.getChildren().remove(signOutButton);
        //root.setTop(headerBar1);
        root.setBottom(footerBar);
        return root;
    }

    private void createLoginPage(){
        Text usernameText = new Text("Username");
        Text passwordText = new Text("Password");

        TextField userName = new TextField("");
        userName.setPromptText("Enter username");
        PasswordField password = new PasswordField();
        password.setText("");
        password.setPromptText("Enter Password");
        Label messageLabel = new Label();

        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String uname = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(uname,pass);
                if (loggedInCustomer != null){
                    welcomeLabel.setText("Welcome "+loggedInCustomer.getName());
                   welcomeLabel.setAlignment(Pos.CENTER);
                    headerBar.getChildren().add(signOutButton);
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().remove(loginPage);
                    body.getChildren().add(productPage);

                }else {
                    messageLabel.setText("Error: wrong username or password");
                }

            }
        });
        loginPage = new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(usernameText,0,0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(loginButton,1,2);
        loginPage.add(messageLabel,1,3);
    }

    private void createSignupPage(){
        Text nameText = new Text("Full Name");
        Text mobileText = new Text("Mobile No.");
        Text emailText = new Text("Email");
        Text passwordText = new Text("Password");

        TextField name = new TextField();
        TextField mobile = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();


        Label messageLabel = new Label();

        Button signupButton = new Button("Signup");

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if (email.getText().isEmpty() || mobile.getText().isEmpty() || name.getText().isEmpty() || password.getText().isEmpty()){
                        messageLabel.setText("All fields are required");
                        return;
                    }
                    String uemail = email.getText();
                    String uname = name.getText();
                    String umobile = mobile.getText();

                    MessageDigest md = MessageDigest.getInstance("SHA-512");
                    String hashedPassword = new String(md.digest(password.getText().getBytes(StandardCharsets.UTF_8)));
                    //System.out.println(hashedPassword);
                    //crete new customer and add to database
                    Customer customer = new Customer(uname,uemail,umobile,hashedPassword);
                    //adding to db
                //    boolean res = Signup.customerLogin(customer);
                 boolean res= SignUp.customerLogin(customer);
                        if (res){
                        createDialog("Registered Successfully");
                    }else {
                        createDialog("Registration failed");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        signupPage = new GridPane();
        signupPage.setVgap(10);

        signupPage.setStyle("-fx-border-color: black;-fx-border-width: 2pt;-fx-padding: 10pt;-fx-max-width: 300pt");
        signupPage.setAlignment(Pos.CENTER);
        signupPage.setHgap(10);
        signupPage.setVgap(10);
        signupPage.add(nameText,0,0);
        signupPage.add(name,1,0);
        signupPage.add(emailText,0,1);
        signupPage.add(email,1,1);
        signupPage.add(mobileText,0,2);
        signupPage.add(mobile,1,2);
        signupPage.add(passwordText,0,3);
        signupPage.add(password,1,3);
        signupPage.add(messageLabel,0,4);
        signupPage.add(signupButton,1,4);

    }

    private void createHeaderBar(){



        Button homeButton = new Button();
        Image logo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\home.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(85);
        logoView.setFitHeight(40);
        homeButton.setGraphic(logoView);
        TextField search = new TextField();
        search.setPrefWidth(250);
        search.setPrefHeight(50);
        search.setPromptText("Type to search");

        Button searchButton = new Button();
       Button cartButton = new Button();
     //  Button orderButton =new Button();
           signInButton = new Button();
        signUpButton = new Button();
        signOutButton=new Button();
        Image signinlogo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\login.jpg");
        ImageView signinlogoView = new ImageView(signinlogo);
        signinlogoView.setFitWidth(40);
        signinlogoView.setFitHeight(40);
        signInButton.setGraphic(signinlogoView);
        Image signoutlogo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\logout.jpeg");
        ImageView signoutlogoView = new ImageView(signoutlogo);
        signoutlogoView.setFitWidth(40);
        signoutlogoView.setFitHeight(40);
        signOutButton.setGraphic(signoutlogoView);
        Image cartlogo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\carto.jpg");
        ImageView cartologoView = new ImageView(cartlogo);
        cartologoView.setFitWidth(40);
        cartologoView.setFitHeight(40);
        cartButton.setGraphic(cartologoView);
        Image searchlogo=new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\search.png");
        ImageView searchlogoview=new ImageView(searchlogo);
        searchlogoview.setFitWidth(40);
        searchlogoview.setFitHeight(40);
        searchButton.setGraphic(searchlogoview);

//        Image ordlogo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\ordericon.jpeg");
//        ImageView ordlogoView = new ImageView(ordlogo);
//          ordlogoView.setFitWidth(40);
//        ordlogoView.setFitHeight(40);
//        orderButton.setGraphic(ordlogoView);
        Image signuplogo = new Image("C:\\Users\\saidu\\IdeaProjects\\MainProject\\images\\signup.jpeg");
        ImageView signuplogoView = new ImageView(signuplogo);
        signuplogoView.setFitWidth(40);
        signuplogoView.setFitHeight(40);
        signUpButton.setGraphic(signuplogoView);

        headerBar = new HBox();
        headerBar.setSpacing(10);
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setStyle("-fx-background-color:lime");
        headerBar1=new HBox();
        //headerBar1.getChildren().addAll(homeButton,search,searchButton,signOutButton,cartButton,orderButton);
      //  headerBar.getChildren().addAll(homeButton,search,searchButton,signInButton,signUpButton);
      headerBar.getChildren().addAll(homeButton,search,searchButton,signInButton,signUpButton,cartButton,signOutButton);
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                //body.getChildren().add(signOutButton);
                body.getChildren().add(loginPage);
               headerBar.getChildren().remove(signInButton);
                headerBar.getChildren().remove(signUpButton);
                footerBar.setVisible(true);
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                VBox cartPage = productList.getProductsInCart(itemsInCart);
                cartPage.setAlignment(Pos.CENTER);
                cartPage.setSpacing(10);
                cartPage.getChildren().add(placeOrderButton);
                body.getChildren().clear();
                body.getChildren().add(cartPage);
                footerBar.setVisible(false);
            }
        });
        placeOrderButton.setAlignment(Pos.CENTER);
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart == null){
                    createDialog("Please add items in cart to place order");
                    return;
                }
                if (loggedInCustomer == null){
                    createDialog("Please login first to place order");
                    return;
                }

                int count = Order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if (count != 0){
                    createDialog("Order placed for "+count+" items successfully");
                }else {
                    createDialog("Order failed");
                }
            }

        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if (loggedInCustomer == null && !headerBar.getChildren().contains(signInButton))
                    headerBar.getChildren().add(signInButton);
               //else if (loggedInCustomer!=null && !headerBar.getChildren().contains(signInButton))
                   // headerBar.getChildren().add(signOutButton);
            }
        });
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(headerBar);
                body.getChildren().remove(signOutButton);
            }
        });
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signupPage);
            }
        });
//        orderButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                body.getChildren().clear();
//                if(loggedInCustomer==null)
//                    createDialog("Please login first to open orders");
//                if (loggedInCustomer!=null)
//
//                return;
//
//
//
//            }
//        });
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                headerBar.getChildren().removeAll(welcomeLabel,signOutButton);
                loggedInCustomer=null;
                footerBar.setVisible(true);
                if (loggedInCustomer == null && !headerBar.getChildren().contains(signInButton))
                    headerBar.getChildren().add(signInButton);

            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String result=search.getText();

            }
        });
    }

    private void createFooterBar(){

        Button buyNowButton = new Button("Buy Now");
        Button addToCartButton = new Button("Add To Cart");

        footerBar = new HBox();
        footerBar.setSpacing(10);
        footerBar.setPadding(new Insets(10));
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null){
                    createDialog("Please select product to place order");
                    return;
                }
                if (loggedInCustomer == null){
                    createDialog("Please login first to place order");
                    return;
                }

                boolean count = Order.placeOrder(loggedInCustomer,product);
                if (count){

                    createDialog("Order placed successfully");
                }else {
                    createDialog("Order failed");
                }
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null){
                    createDialog("Please select product to add in cart");
                    return;
                }
                itemsInCart.add(product);
                createDialog("Item added in cart");
            }
        });

    }

    void createDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.show();
    }
}
