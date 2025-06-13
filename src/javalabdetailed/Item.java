/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

/**
 *
 * @author zaini
 */
public class Item {
    protected int id;
    protected String name;
    protected String category;
    protected double price;
    protected String size;
    
   
    public Item(){
        this.id = 0;
        this.name = "";
        this.category = "";
        this.price = 0.0;
        this.size = "small";
    }
    public Item(int id, String name, String category, String size, double price){
        this.id = id;
        this.name = name;
        this.size = size;
        this.category = category;
        this.price = price;
    }
    public int getID(){
        return id;
    }
    public double getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public String getSize(){
        return size;
    }
    
    public void setID(int id){
        this.id = id;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCategory(String category){
        this.category = category;
        
    }
    public void setSize(String size) {
        this.size = size;
    }
    
   
 
}

