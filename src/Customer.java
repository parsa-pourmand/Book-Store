/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author parsapourmand
 */
import java.util.ArrayList;
import java.io.File;  
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;


public class Customer extends User{
    
    private  ArrayList<Book> books = new ArrayList<Book>();
    private double point;
    private States status;
    private static File myfile = new File("Customer");
    private double temp;
    
    public Customer(String username, String password, double point, boolean flag ){
        if(flag == true){
        this.username = username;
        this.password = password;
        this.point = point;
        setStatus();
        try {
                FileWriter myWriter = new FileWriter("Customer", true);
                myWriter.write(this.username + "," + this.password + "," + this.point + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }else{
            
            this.username = username;
            this.password = password;
            this.point = point;
            setStatus();
        }
    }
    
    public static void read() {
        
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("Customer"));
            String line = null;
            
             
            while ((line = br.readLine()) != null) {
               
                String[] values = line.split(",");
                
                Owner.customers.add(new Customer(values[0],values[1], Double.valueOf(values[2]),false));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void customer_file_update(){
            
        try {
                myfile.delete();
                myfile = new File("Customer");
                FileWriter myWriter = new FileWriter("Customer", true);
                for(int i = 0; i<Owner.customers.size(); i++){
                    myWriter.write(Owner.customers.get(i).getUsername() + "," + Owner.customers.get(i).getPassword() + "," + Owner.customers.get(i).getPoint() +"\n");
                }
                    myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setpoint(double points){
        if(points>=0){
            this.point = points;
        }
    }
    
    public double getPoint(){
          return this.point;
    }
    
    public States getStatus(){
        return this.status;
    }
    
    public void setStatus(){
        if(this.point >= 0 && this.point < 1000){
            status = new Silver();
        } else if(this.point >= 1000)
        {
            status = new Gold();
        }else{
            status = null;
        }
    }
    
    public ArrayList<Book> getBooks(){
        return books;
    }
    
    public void buyBookWithMoney(Book b){
        
        this.books.add(b);
        this.point = this.point + (10 * Integer.valueOf(b.getPrice()));
        setStatus();        
    }
    
    public double buyBookWithPoints(Book b){
        temp =0;
       if(this.point >= 100 * Double.valueOf(b.getPrice())){
           this.point = this.point - (100 * Double.valueOf(b.getPrice()));
           setStatus();
           b.setPrice(String.valueOf(temp));
           this.books.add(b);
           
        }else{
          temp = Double.valueOf(b.getPrice()) - (Math.floor(this.point/100));
          this.point = (10*temp);
          setStatus();
          b.setPrice(String.valueOf(temp));
          this.books.add(b);
       }
       
       return temp;
    }
    
    @Override
    public boolean login(String username, String password){
        
        if(this.username.trim().equals(username) && this.password.trim().equals(password)){
            return true;
        }else{
            return false;
        }
    }
    
    
}
