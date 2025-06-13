/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

import java.util.ArrayList;

/**
 *
 * @author mudasser
 */
public class Order {
    private int id;
    private String name;
    private String email;
    private String specialInstructions;
    private ArrayList<OrderItem> items;
    
    public Order(int id, String name, String email, String sI){
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialInstructions = sI;
        this.items = new ArrayList<>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setSI(String sI){
        this.specialInstructions = sI;
    }
    public void setOrderItems(ArrayList<OrderItem> items){
        this.items = items;
    }
    
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getSI(){
        return specialInstructions;
    }
    public ArrayList<OrderItem> getOrderItems(){
        return items;
    }
    
}
