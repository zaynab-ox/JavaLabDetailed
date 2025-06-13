/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

/**
 *
 * @author mudasser
 */
public class Table {
    private boolean status;
    private Order order;
    private int id;
    
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }
    public void setOrder(Order order){
        this.order = order;
    }
    public Order getOrder(){
        return order;
    }
}
