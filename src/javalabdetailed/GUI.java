/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zaini
 */
public class GUI {

    boolean isClean;
    boolean edit;

    public void menuBuilder(JTabbedPane pane) {
        JPanel wrapper = new JPanel();
//        ImageIcon logoImage = new ImageIcon(Main.class.getResource("/Media/logo.png"));
        JLabel logo = new JLabel("Restaurent");
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setFont(new Font("Arial", Font.BOLD, 21));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        JPanel container = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();

        container.setLayout(new GridLayout(1, 2, 20, 0));
        container.add(left);
        container.add(right);
        left.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        right.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        wrapper.add(logo);
        wrapper.add(container);
        pane.addTab("Menu Builder", wrapper);

        entryForm(left, right, new Item());
        menu(right, left);
    }

    public void entryForm(JPanel form, JPanel right, Item pItem) {
        edit = false;
        final int editID = pItem.getID();
        MenuBuilder builder = new MenuBuilder();
        form.setLayout(new GridLayout(16, 1, 0, 5));
        JLabel title = new JLabel("Menu Builder Form");
        title.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel nameLabel = new JLabel("Item Name");
        JTextField nameField = new JTextField(40);
        JLabel nameErr = new JLabel("");

        JLabel categLabel = new JLabel("Category");
        JTextField categField = new JTextField(40);
        JLabel categErr = new JLabel("");

        JLabel sizeLabel = new JLabel("Item Size");
        JTextField sizeField = new JTextField(40);
        JLabel sizeErr = new JLabel("");

        JLabel priceLabel = new JLabel("Item Price");
        JTextField priceField = new JTextField(40);
        JLabel priceErr = new JLabel("");

        JButton saveItemBtn = new JButton("Save");
        saveItemBtn.setBackground(Color.decode("#7dbf73"));
        saveItemBtn.setForeground(Color.DARK_GRAY);

        form.add(title);
        form.add(nameLabel);
        form.add(nameField);
        form.add(nameErr);
        form.add(categLabel);
        form.add(categField);
        form.add(categErr);
        form.add(sizeLabel);
        form.add(sizeField);
        form.add(sizeErr);
        form.add(priceLabel);
        form.add(priceField);
        form.add(priceErr);
        form.add(new JLabel(""));
        form.add(saveItemBtn);
        
        if(!pItem.getName().isEmpty()){
            nameField.setText(pItem.getName());
            categField.setText(pItem.getCategory());
            sizeField.setText(pItem.getSize());
            priceField.setText("" + pItem.getPrice());
            edit = true;
        } else {
            edit = false;
        }

        ArrayList<JTextField> fields = new ArrayList<>();
        fields.add(sizeField);
        fields.add(nameField);
        fields.add(categField);

        ArrayList<JLabel> errors = new ArrayList<>();
        errors.add(nameErr);
        errors.add(sizeErr);
        errors.add(priceErr);
        errors.add(categErr);

        for (JLabel error : errors) {
            error.setForeground(Color.decode("#ff6e92"));
            error.setFont(new Font("Arial", Font.PLAIN, 11));
        }

        saveItemBtn.addActionListener(e -> {
            if (nameField.getText().isEmpty()) {
                nameErr.setText("Item Name is required");
            } else {
                nameErr.setText("");
            }

            if (categField.getText().isEmpty()) {
                categErr.setText("Item Category is required");
            } else {
                categErr.setText("");
            }

            if (sizeField.getText().isEmpty()) {
                sizeErr.setText("Item Size is required");
            } else {
                sizeErr.setText("");
            }

            if (priceField.getText().isEmpty()) {
                priceErr.setText("Item Name is required");
            } else {
                priceErr.setText("");
            }

            isClean = true;
            for (JLabel err : errors) {
                if (!err.getText().isEmpty()) {
                    isClean = false;
                    break;
                }
            }

            if (isClean) {
                Item item = new Item(0, nameField.getText(), categField.getText(), sizeField.getText(), Double.parseDouble(priceField.getText()));
                if(!edit){
                    builder.addItem(item);
                    System.out.println("Item added successfully");
                } else {
                    item.setID(editID);
                    builder.editItem(item);
                    System.out.println("Item Updated successfully");
                }
                nameField.setText("");
                categField.setText("");
                priceField.setText("");
                sizeField.setText("");
                menu(right, form);
                right.revalidate();
            } else {
                System.out.println("Errors Exist");
            }
        });

    }
    public void orderEntryForm(JPanel form, JPanel orders, JPanel orderPage) {
        MenuBuilder builder = new MenuBuilder();
        form.setLayout(new GridLayout(16, 1, 0, 5));
        JLabel title = new JLabel("Customer Information");
        title.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel nameLabel = new JLabel("Customer Name");
        JTextField nameField = new JTextField(40);
        JLabel nameErr = new JLabel("");

        JLabel emailLabel = new JLabel("Cusomer Email");
        JTextField emailField = new JTextField(40);
        JLabel emailErr = new JLabel("");

        JLabel descLabel = new JLabel("Special Instructions");
        JTextField descField = new JTextField(40);


        JButton saveItemBtn = new JButton("Process Order");
        saveItemBtn.setBackground(Color.decode("#7dbf73"));
        saveItemBtn.setForeground(Color.DARK_GRAY);
        
        JButton cancelBtn = new JButton("Cancel");
        saveItemBtn.setBackground(Color.decode("#7dbf73"));
        saveItemBtn.setForeground(Color.DARK_GRAY);
        
        cancelBtn.addActionListener(e -> {
            orderPage.setVisible(false);
            orders.setVisible(true);
        });

        form.add(title);
        form.add(nameLabel);
        form.add(nameField);
        form.add(nameErr);
        form.add(emailLabel);
        form.add(emailField);
        form.add(emailErr);
        form.add(descLabel);
        form.add(descField);
        form.add(new JLabel(""));
        form.add(saveItemBtn);
        form.add(cancelBtn);

        ArrayList<JTextField> fields = new ArrayList<>();
        fields.add(descField);
        fields.add(nameField);
        fields.add(emailField);

        ArrayList<JLabel> errors = new ArrayList<>();
        errors.add(nameErr);
        errors.add(emailErr);

        for (JLabel error : errors) {
            error.setForeground(Color.decode("#ff6e92"));
            error.setFont(new Font("Arial", Font.PLAIN, 11));
        }

        saveItemBtn.addActionListener(e -> {
            if (nameField.getText().isEmpty()) {
                nameErr.setText("Customer Name is required");
            } else {
                nameErr.setText("");
            }

            if (emailField.getText().isEmpty()) {
                emailErr.setText("Customer Email is required");
            } else {
                emailErr.setText("");
            }


            isClean = true;
            for (JLabel err : errors) {
                if (!err.getText().isEmpty()) {
                    isClean = false;
                    break;
                }
            }

            if (isClean) {
                System.out.println("Order Placed");
                nameField.setText("");
                descField.setText("");
            } else {
                System.out.println("Errors Exist (order)");
            }
        });

    }

    public void menu(JPanel container, JPanel form) {
        container.removeAll();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        MenuBuilder builder = new MenuBuilder();
        JLabel title = new JLabel("Menu");
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(title);

        // Main panel to hold heading and item rows
        JPanel itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Row Heading
        JPanel itemRowHeading = new JPanel(new GridLayout(1, 5));
        itemRowHeading.setBackground(Color.LIGHT_GRAY);
        itemRowHeading.add(new JLabel("Name"));
        itemRowHeading.add(new JLabel("Category"));
        itemRowHeading.add(new JLabel("Price"));
        itemRowHeading.add(new JLabel("Size"));
        itemRowHeading.add(new JLabel("Actions"));
        itemRowHeading.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Fix height
        itemRowHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemsContainer.add(itemRowHeading);

        // Data rows
        ArrayList<Item> items = builder.getItems();
        for (Item item : items) {
            JPanel itemRow = new JPanel(new GridLayout(1, 5));
            itemRow.add(new JLabel(item.getName()));
            itemRow.add(new JLabel(item.getCategory()));
            itemRow.add(new JLabel(String.valueOf(item.getPrice())));
            itemRow.add(new JLabel(item.getSize()));
            JButton delete = new JButton("Delete");
            JButton edit = new JButton("Edit");
//            ImageIcon deleteIcon = new ImageIcon(Main.class.getResource("/images/delete.png"));
//            JLabel icon = new JLabel(deleteIcon);
//            icon.setAlignmentX(Component.CENTER_ALIGNMENT);
//            delete.add(icon);
            JPanel actions = new JPanel();
            actions.add(edit);
            actions.add(delete);
            itemRow.add(actions);
            delete.addActionListener(e -> {
                builder.deleteItem(item.getID());
                menu(container, form);
            });
            edit.addActionListener(e -> {
                form.removeAll();
                entryForm(form, container, builder.getItem(item.getID()));
            });
            itemRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            itemRow.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsContainer.add(itemRow);
        }

        // Add to scroll pane
        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(scrollPane);

        // Refresh the container
        container.revalidate();
        container.repaint();
    }
    public void orderMenu(JPanel container) {
        container.removeAll();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        OrderManager manager = new OrderManager();
        JLabel title = new JLabel("Menu");
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(title);

        // Main panel to hold heading and item rows
        JPanel itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Row Heading
        JPanel itemRowHeading = new JPanel(new GridLayout(1, 4));
        itemRowHeading.setBackground(Color.LIGHT_GRAY);
        itemRowHeading.add(new JLabel("Name"));
        itemRowHeading.add(new JLabel("Category"));
        itemRowHeading.add(new JLabel("Price"));
        itemRowHeading.add(new JLabel("Size"));
        itemRowHeading.add(new JLabel("Quantity"));
        itemRowHeading.add(new JLabel("Actions"));
        itemRowHeading.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Fix height
        itemRowHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemRowHeading.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        itemsContainer.add(itemRowHeading);

        // Data rows
        ArrayList<OrderItem> items = manager.getOrderItems();
        
        for (Item item : items) {
            JPanel itemRow = new JPanel(new GridLayout(1, 4));
            itemRow.add(new JLabel(item.getName()));
            itemRow.add(new JLabel(item.getCategory()));
            itemRow.add(new JLabel(String.valueOf(item.getPrice())));
            itemRow.add(new JLabel(item.getSize()));
            itemRow.add(new JLabel("0"));
            itemRow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            JButton plus = new JButton("+");
            JButton min = new JButton("-");
            
            plus.addActionListener(e -> {
                
            });
            
            JPanel actions = new JPanel();
            actions.add(plus);
            actions.add(min);
            itemRow.add(actions);
            itemRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            itemRow.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsContainer.add(itemRow);
        }

        // Add to scroll pane
        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(scrollPane);

        // Refresh the container
        container.revalidate();
        container.repaint();
    }
    
    public void ordersPage(JTabbedPane pane){
        MenuBuilder builder = new MenuBuilder();
        JPanel processOrderPanel = new JPanel();
        processOrderPanel.setLayout(new GridLayout(1, 2, 20, 0));
        JPanel orders = new JPanel();
        JLabel title = new JLabel("Place Orders");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        orders.add(title);
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(4,4,5,5));
        
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        
        JPanel menu = new JPanel();
        
        processOrderPanel.setVisible(false);
        
        orderEntryForm(form,container, processOrderPanel);
        orderMenu(menu);
                
        orders.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        container.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        ArrayList<Table> tables = new ArrayList<>();
        for(int i = 0; i < 16; i++){
            tables.add(new Table());
        }
        int i = 1;
        for(Table table: tables){
            JButton tableBtn = new JButton("Table " + (i++));
            container.add(tableBtn);
            tableBtn.addActionListener(e -> {
                container.setVisible(false);
                processOrderPanel.setVisible(true);
            });
        }
        processOrderPanel.add(form);
        processOrderPanel.add(menu);
        orders.add(container);
        orders.add(processOrderPanel);
        pane.addTab("Orders", orders);
    }
}
