/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

/**
 *
 * @author mudasser
 */
public class OrderItem extends Item{
    protected int quantity;
    
    public OrderItem(int id, String name, String category, Double Price, String size){
        this.name = name;
        this.id = id;
        this.category = category;
        this.price = price;
        this.size = size;
        this.quantity = 0;
    }
    
    public void setQuantity(int qty){
        this.quantity = qty;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void incrementQuantity(){
        this.quantity++;
    }
}
