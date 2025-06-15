/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

/**
 *
 * @author mudasser
 */
public class Bill {
    private int id;
    private Order order;
    private double totalAmount;
    private String date;
    
    public Bill(int id, Order order){
        this.id = id;
        this.order = order;
        this.totalAmount = 0;
        calculateBill();
    }
    public void calculateBill(){
        for(OrderItem item : order.getOrderItems()){
            totalAmount += (item.getPrice() * item.getQuantity());
        }
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    public void setOrder(Order order){
        this.order = order;
    }
    public Order getOrder(){
        return order;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }
    public double getTotalAmount(){
        return totalAmount;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
}
