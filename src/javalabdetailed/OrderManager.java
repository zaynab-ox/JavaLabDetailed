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

    public ArrayList<OrderItem> getOrderItems() {
        ArrayList<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection conn = DBManager.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                items.add(new OrderItem(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"), rs.getString("size")));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching items " + e);
        }
        return items;
    }

    public int storeOrder(Bill bill, int tableID) {
        String sql = "INSERT INTO orders(name, email, amount, special_instructions, table_id) VALUES (?,?,?,?,?)";
        int generatedId = -1;

        try (Connection conn = DBManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bill.getOrder().getName());
            stmt.setString(2, bill.getOrder().getEmail());
            stmt.setDouble(3, bill.getTotalAmount());
            stmt.setString(4, bill.getOrder().getSI());
            stmt.setInt(5, tableID);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e);
        }

        return generatedId;
    }
    public ArrayList<Bill> getOrders(){
        ArrayList<Bill> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try(Connection conn = DBManager.getConnection();
                Statement stmt = conn.createStatement()){
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Bill bill = new Bill(rs.getInt("id"), new Order(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("special_instructions")));
                bill.setTotalAmount(rs.getDouble("amount"));
                bill.setDate(rs.getString("date"));
                orders.add(bill);
            }
        } catch (SQLException e){
            System.out.println("Error Fetching Reports" + e);
        }
        return orders;
    }

}
