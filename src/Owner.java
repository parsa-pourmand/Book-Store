/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author parsapourmand
 */
import java.util.ArrayList;
public class Owner extends User{
    private int i = 0;
    private int j;
    private static Owner instance;
    public static ArrayList<Book>  books = new ArrayList<Book>();
    public static ArrayList<Customer>  customers = new ArrayList<Customer>();
   
    
    private Owner(String username, String password){
        
        this.username = username;
        this.password = password;
    }
    
    public void addCustomer(Customer c){
        customers.add(c);
    }
    
    public static void addBook(Book b){
        books.add(b);
    }
    
    public void deleteBook(Book b){
        if(books.contains(b)){
            books.remove(b);
        }else{
            System.out.println("The Book does not exist!");
        }
    }
    
    public void deleteCustomer(Customer c){
        if(customers.contains(c)){
            books.remove(c);
        }else{
            System.out.println("The customer does not exist!");
        }
    }
    
    public Book getBooks(String name, double price){
        for(i=0; i<books.size(); i ++){
            if(books.get(i).getName().equals(name) && Integer.valueOf(books.get(i).getPrice()) == price){
                j = i;
            }
        }
        return books.get(j);
    }
    
    public Customer getcustomer(String username){
        for(i=0; i<customers.size(); i ++){
            if(customers.get(i).getUsername().equals(username)){
                j = i;
            }
        }
        return customers.get(j);
    }
    
    @Override
    public boolean login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        }else{
            return false;
            }
    }
    
    
    
    public static Owner getInstance(){
        
        if(instance == null){
            instance = new Owner("admin", "admin");
        }
        return instance;
    }
      
}
