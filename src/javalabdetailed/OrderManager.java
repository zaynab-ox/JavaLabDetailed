/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

import DB.DBManager;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author mudasser
 */
public class OrderManager {
    public ArrayList<OrderItem> getOrderItems(){
        ArrayList<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try(Connection conn = DBManager.getConnection();
                Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                items.add(new OrderItem(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"), rs.getString("size")));
            }
            
        } catch (SQLException e){
            System.out.println("Error fetching items " + e);
        }
        return items;
    }
}
