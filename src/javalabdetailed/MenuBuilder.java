/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

import DB.DBManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author zaini
 */
public class MenuBuilder {
    public void addItem(Item item){
        String sql = "INSERT INTO menu(name,price,category,size) VALUES (?,?,?,?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getSize()); 
            
            pstmt.execute();

        } catch (SQLException e) {
            System.err.println("Error adding Item: " + e.getMessage());
        }
    }
    public ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try(Connection conn = DBManager.getConnection();
                Statement stmt = conn.createStatement()){
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getString("size"), rs.getDouble("price")));
            }
        } catch (SQLException e){
            System.out.println("Error fetching items " + e);
        }
        return items;
    }
    public void deleteItem(int id){
        String sql = "DELETE FROM menu WHERE id=?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            stmt.execute();
        } catch (SQLException e){
            System.out.println("Error Deleting Item " + e);
        }
    }
    public Item getItem(int id){
        String sql = "SELECT * FROM menu WHERE id=?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Item(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getString("size"), rs.getDouble("price"));
            }
            
            stmt.execute();
        } catch (SQLException e){
            System.out.println("Error Fetching Item " + e);
        }
        return new Item();
    }
    public boolean editItem(Item item){
        String sql = "UPDATE menu SET name=?,price=?,category=?,size=? WHERE id=?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getSize()); 
            pstmt.setInt(5, item.getID()); 
            
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error Updating Item: " + e.getMessage());
        }
        return false;
    }
}
