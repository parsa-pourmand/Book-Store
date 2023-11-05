/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;



/**
 *
 * @author parsapourmand
 */
public class Login extends Application {
    //definig instance variables
    private Stage ps;
    private Owner owner = Owner.getInstance();
    private Customer customer;
    private Scene main_scene;
    
    //two observable arraylist for book and customer tables
    private final ObservableList<Book> Book_data = FXCollections.observableArrayList();
    private final ObservableList<Customer> Customer_data = FXCollections.observableArrayList();
    
    
   @Override
    public void start(Stage primaryStage) {
        
        this.ps = primaryStage;
        
        this.ps.setTitle("Bookstore App");
       
        //add customers to the array everytime we run the program by reading from file
       Customer.read();
       for(int i=0; i<owner.customers.size(); i++){
            
            Customer_data.add(owner.customers.get(i));
        }
        //the first scene when we run the program is login
        GridPane pane1 = Login();
         
        main_scene = new Scene(pane1, 300, 275);
        this.ps.setScene(main_scene);
        this.ps.show();
    }
    
    //This function contains all the information for login screen.
    public GridPane Login() {
        
        owner.books.clear();
        Book_data.clear();
        Book.read();
        for(int i=0; i<owner.books.size(); i++){
            
            Book_data.add(owner.books.get(i));
        }
        

        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.CENTER);
        pane1.setHgap(10);
        pane1.setVgap(10);
        pane1.setPadding(new Insets(100, 100, 50, 50));
        
        Text scenetitle = new Text("Welcome to the Bookstore App");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        pane1.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        pane1.add(userName, 0, 1);

        TextField userTextField = new TextField();
        pane1.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        pane1.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pane1.add(pwBox, 1, 2);
        
        Button login = new Button("Login");
         HBox hbBtn = new HBox(10);
         hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
         hbBtn.getChildren().add(login);
         pane1.add(hbBtn, 1, 4);
         
         final Text actiontarget = new Text();
         pane1.add(actiontarget, 1, 6);
         login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean userFlg = false;
                actiontarget.setFill(Color.FIREBRICK);
                if(owner.login(userTextField.getText(), pwBox.getText())){
                    ps.setScene(Ownerview());
                    userFlg = true;
                }else{
                    
                    for(int i=0; i<owner.customers.size(); i++){
                        
                        if(owner.customers.get(i).login(userTextField.getText(), pwBox.getText())){
                            userFlg = true;
                            customer = owner.customers.get(i);
                            ps.setScene(customerView());
                        }       
                    }
                    
                }
                
                if(!userFlg){
                    actiontarget.setText("The Username or Password is incorrect.");
                }
            }
        });
    
         return pane1;
    }
    
    //this function contains all information for Owner view screen.
    public Scene Ownerview() {
        Scene scene;
        GridPane gp1 = new GridPane();
        
        gp1.setAlignment(Pos.CENTER); 
        Button books = new Button("     Books     ");
        HBox bkBtn = new HBox(20);
        bkBtn.setAlignment(Pos.CENTER);
        bkBtn.setPadding(new Insets(5,5,5,5));
        bkBtn.getChildren().add(books);
        gp1.add(bkBtn, 500, 1000);
        
        final Text actiontarget = new Text();
         gp1.add(actiontarget, 1, 6);
        books.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                
                ps.setScene(Owner_Books());
                ps.show();
            
            }
        });
        
        Button customers = new Button("Customers");
        HBox csBtn = new HBox(20);
        csBtn.setAlignment(Pos.CENTER);
        csBtn.setPadding(new Insets(5,5,5,5));
        csBtn.getChildren().add(customers);
        gp1.add(csBtn, 500, 100);
        
        final Text actiontarget2 = new Text();
         gp1.add(actiontarget2, 1, 6);
        customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget2.setFill(Color.FIREBRICK);
                ps.setScene(Owner_Customer());
                ps.show();
            }
        });
        
        Button logout = new Button("  Logout  ");
        HBox lgBtn = new HBox(20);
        lgBtn.setAlignment(Pos.CENTER);
        lgBtn.setPadding(new Insets(5,5,5,5));
        lgBtn.getChildren().add(logout);
        gp1.add(lgBtn, 500, 1500);
        
        final Text actiontarget3 = new Text();
         gp1.add(actiontarget3, 1, 6);
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget3.setFill(Color.FIREBRICK);
                Scene scene = new Scene(Login(), 300, 275);
                ps.setScene(scene);
                ps.show();
            
            }
        });
        scene = new Scene(gp1, 500,200);
        return scene;
    }
    
    
    
    //this function contains all information for Owner Book screen.
    public Scene Owner_Books(){
        TableView<Book> owner_Book_Table = new TableView<Book>();
        Scene scene = new Scene(new Group());
        final HBox hb = new HBox();

        final Label label = new Label("Books");
        label.setFont(new Font("Arial", 20));
        
        owner_Book_Table.setEditable(true);
 
        TableColumn name = new TableColumn("Name");
        name.setMinWidth(300);
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("Name"));
        
       
        
        TableColumn price = new TableColumn("Price");
        price.setMinWidth(300);
        price.setCellValueFactory(new PropertyValueFactory<Book, String>("Price"));
        
        
        
        owner_Book_Table.setItems(Book_data);
        owner_Book_Table.getColumns().addAll(name, price);
        
        
        
        final TextField name2 = new TextField();
        name2.setPromptText("Name");
        name2.setMaxWidth(name.getPrefWidth());
        final TextField price2 = new TextField();
        price2.setMaxWidth(price2.getPrefWidth());
        price2.setPromptText("Price");

 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                if(name2.getText().trim().isEmpty() || price2.getText().trim().isEmpty()){
                    return;
                }else{
                    Book sample = new Book(name2.getText(),price2.getText(), true);
                    owner.addBook(sample);
                    Book_data.add(sample);
                    name2.clear();
                    price2.clear();
                }
            }
        });
        owner_Book_Table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e) {
            Book selectedItem = owner_Book_Table.getSelectionModel().getSelectedItem();
                owner.books.remove(owner_Book_Table.getSelectionModel().getSelectedIndex());
                owner_Book_Table.getItems().remove(selectedItem);
                Book.book_file_update();
            }
        });
        
        final Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                
                ps.setScene(Ownerview());
                ps.show();
            }
        });
        
        hb.getChildren().addAll(name2, price2, addButton,deleteButton,backButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, owner_Book_Table, hb);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        
        return scene;
    }
    
    //this function contains all information for Owner customer screen.
    public Scene Owner_Customer(){
        TableView<Customer> owner_Customer_Table = new TableView<Customer>();
        Scene scene = new Scene(new Group());
        final HBox hb = new HBox();

        final Label label = new Label("Customers");
        label.setFont(new Font("Arial", 20));
        
        owner_Customer_Table.setEditable(true);
 
        TableColumn username = new TableColumn("Username");
        username.setMinWidth(300);
        username.setCellValueFactory(new PropertyValueFactory<Customer, String>("Username"));
        
       
        
        TableColumn password = new TableColumn("Password");
        password.setMinWidth(300);
        password.setCellValueFactory(new PropertyValueFactory<Customer, String>("Password"));
        
        
        TableColumn point = new TableColumn("Point");
        point.setMinWidth(300);
        point.setCellValueFactory(new PropertyValueFactory<Customer, Double>("point"));
        
        
        
        owner_Customer_Table.setItems(Customer_data);
        owner_Customer_Table.getColumns().addAll(username, password,point);
        
        final TextField username2 = new TextField();
        username2.setPromptText("Username");
        username2.setMaxWidth(username.getPrefWidth());
        
        final TextField password2 = new TextField();
        password2.setMaxWidth(password.getPrefWidth());
        password2.setPromptText("Password");
        
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                if(username2.getText().trim().isEmpty() || password2.getText().trim().isEmpty()){
                    return;
                }else{
                    Customer sample = new Customer(username2.getText(),password2.getText(), 0 , true);
                    owner.addCustomer(sample);
                    Customer_data.add(sample);
                    username2.clear();
                    password2.clear();
                }
            }
        });
        
        owner_Customer_Table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Customer selectedItem = owner_Customer_Table.getSelectionModel().getSelectedItem();
                owner_Customer_Table.getItems().remove(selectedItem);
                owner.customers.remove(owner_Customer_Table.getSelectionModel().getSelectedIndex()+1);
                Customer.customer_file_update();
            }
        });
        
        final Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                
                ps.setScene(Ownerview());
                ps.show();
            }
        });
        
        hb.getChildren().addAll(username2, password2, addButton,deleteButton,backButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, owner_Customer_Table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        
        return scene;  
    }
    
    //this function contains all information for Customer view screen.
    public Scene customerView(){
        TableView<Book> owner_Book_Table = new TableView<Book>();
        Scene scene = new Scene(new Group());
        final HBox hb = new HBox();
        
        final Label label = new Label("Welcome " + customer.getUsername() + ". You have " + customer.getPoint() + " points. Your State is " + customer.getStatus().getStatus());
        label.setFont(new Font("Arial", 20));
        
        owner_Book_Table.setEditable(true);
 
        TableColumn name = new TableColumn("Name");
        name.setMinWidth(300);
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("Name"));
        
       
        
        TableColumn price = new TableColumn("Price");
        price.setMinWidth(300);
        price.setCellValueFactory(new PropertyValueFactory<Book, String>("Price"));
        
        TableColumn select = new TableColumn("Select");
        select.setMinWidth(100);
        select.setCellValueFactory(new PropertyValueFactory<Book, String>("Select"));
        
        
        owner_Book_Table.setItems(Book_data);
        owner_Book_Table.getColumns().addAll(name, price, select);
        
        final Button buy = new Button("Buy");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                
                for(int i=0; i<owner.books.size(); i++){
                    
                    
                    if(owner.books.get(i).getSelect().isSelected()){
                        
                        Book b = owner.books.get(i);
                        customer.buyBookWithMoney(b);
                    }    
                }
                
                for(int i=0; i<customer.getBooks().size(); i++){
                    
                    owner.books.remove(customer.getBooks().get(i));
                }
                
                Book.book_file_update();
                customer.customer_file_update();
                
                ps.setScene(customer_cost());
                ps.show();
            }
        });
        
        final Button point_buy = new Button("Redeem points & buy");
        point_buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                if(customer.getPoint()==0){
                    return;
                }else{
                for(int i=0; i<owner.books.size(); i++){
                    if(owner.books.get(i).getSelect().isSelected()){
                        Book b = owner.books.get(i);
                        customer.buyBookWithPoints(b);
                        
                    }    
                }
                
                for(int i=0; i<customer.getBooks().size(); i++){
                    
                    owner.books.remove(customer.getBooks().get(i));
                }
                    /*for(int j=0; j<owner.books.size(); j++){
                        if(owner.books.get(j).getSelect().isSelected()){
                            //if(customer.getBooks().get(i).getName().equals(owner.books.get(j).getName())){
                            owner.books.remove(j);
                        }
                    }*/
                
                
                Book.book_file_update();
                customer.customer_file_update();
                
                ps.setScene(customer_cost());
                ps.show();
            }
            }
        });
        
        final Button logout = new Button("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                
                ps.setScene(new Scene(Login(), 300, 275));
                ps.show();
            }
        });
        
        hb.getChildren().addAll( buy,point_buy,logout);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, owner_Book_Table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        return scene;
    }
    
    //this function contains all information for Customer Cost screen.
    public Scene customer_cost(){
        double sum = 0.0;
        Scene scene;
        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.CENTER);
        pane1.setHgap(10);
        pane1.setVgap(10);
        pane1.setPadding(new Insets(100, 100, 100, 100));
        
        for(int i =0; i<customer.getBooks().size(); i++){
            sum = sum + Double.valueOf(customer.getBooks().get(i).getPrice().trim());
        }
        
        Text cost = new Text("Total Cost: " + sum);
        cost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        pane1.add(cost, 0, 0, 2, 1);
        
        Text point = new Text("Points: " + customer.getPoint());
        point.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        pane1.add(point, 0, 0, 2, 7);
        
        Text status = new Text("Status: " + customer.getStatus().getStatus());
        status.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        pane1.add(status, 0, 0, 8, 12);
        customer.getBooks().clear();
        
        Button logout = new Button("Logout");
         HBox hbBtn = new HBox(15);
         hbBtn.setAlignment(Pos.BOTTOM_CENTER);
         hbBtn.getChildren().add(logout);
         pane1.add(hbBtn, 1, 12);
         
         final Text actiontarget3 = new Text();
         pane1.add(actiontarget3, 2, 6);
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget3.setFill(Color.FIREBRICK);
                Scene scene = new Scene(Login(), 300, 275);
                ps.setScene(scene);
                ps.show();
            
            }
        });
        
        scene = new Scene(pane1, 300,275 );
        
        return scene;
    }
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
