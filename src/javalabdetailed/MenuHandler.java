package javalabdetailed;

import DB.DBManager;
import java.sql.*;
import java.util.ArrayList;

public class MenuHandler {

    public void addItem(Item item) {
        String sql = "{CALL add_menu_item(?, ?, ?, ?)}";
        try (Connection conn = DBManager.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getCategory());
            stmt.setString(4, item.getSize());
            stmt.execute();

        } catch (SQLException e) {
            System.err.println("Error adding Item: " + e.getMessage());
        }
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        String sql = """
                     SELECT m.id, m.name, m.price, m.size, c.name AS category
                     FROM menu m
                     JOIN category c ON m.category_id = c.id
                     """;
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("size"),
                        rs.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching items " + e);
        }
        return items;
    }

    public void deleteItem(int id) {
        String sql = "{CALL delete_menu_item(?)}";
        try (Connection conn = DBManager.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Error Deleting Item " + e);
        }
    }

    public Item getItem(int id) {
        String sql = """
                     SELECT m.id, m.name, m.price, m.size, c.name AS category
                     FROM menu m
                     JOIN category c ON m.category_id = c.id
                     WHERE m.id = ?
                     """;
        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("size"),
                        rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error Fetching Item " + e);
        }
        return new Item();
    }

    public boolean editItem(Item item) {
        String sql = "{CALL update_menu_item(?, ?, ?, ?, ?)}";
        try (Connection conn = DBManager.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, item.getID());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getSize());
            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error Updating Item: " + e.getMessage());
        }
        return false;
    }
}
