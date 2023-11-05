/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author parsapourmand
 */
public abstract class User {
    protected String username;
    protected String password;
    
    public abstract boolean login(String username, String password);
    
}
