/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author parsapourmand
 */
import java.io.File;  
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.lang.*;
import javafx.scene.control.CheckBox;
//The class Book contains all the data related to book and the getter and setter methods for them -> name, price
public class Book {
    private String name;
    private String price;
    //The file that all the cunstructed book will save in it.
    private static File myBooks = new File("Book");
    //CheckBox for the time that the customer wants to buy book.
    private CheckBox select;
    
    //Constructor
    //if the flag is true, it will initiate the variables and save in the file, otherwise it will just initiate.
    public Book(String name, String price, boolean flag){
        if(flag == true){
            this.name = name;
            this.price = price;
            this.select = new CheckBox();
            
            try {
                FileWriter myWriter = new FileWriter("Book", true);
                myWriter.write(this.name + "," + this.price + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else{
            this.select = new CheckBox();
            this.name = name;
            this.price = price;
        }
    }
    //It reads the book's name and price from the file and creats an instance of the book. Then it adds the instannce to the Owner books ArrayList.
    public static void read() {
        
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("Book"));
            String line = null;
            
             
            while ((line = br.readLine()) != null) {
               
                String[] values = line.split(",");
                //here the flag is false so when it creats the instance it won't be added to the file again.
                Owner.books.add(new Book(values[0],values[1],false));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPrice(String price){
           this.price = price;
    }
    
    public String getPrice(){
        return this.price;
    }
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select){
        this.select = select;
    }
    
    //This function reads the book's name and price from the owner array and updates the file
    public static void book_file_update(){
            
        try {
                myBooks.delete();
                myBooks = new File("Book");
                FileWriter myWriter = new FileWriter("Book", true);
                for(int i = 0; i<Owner.books.size(); i++){
                    myWriter.write(Owner.books.get(i).getName() + "," + Owner.books.get(i).getPrice() + "\n");
                }
                    myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}
