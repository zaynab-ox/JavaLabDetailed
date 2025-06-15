/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

/**
 *
 * @author zaini
 */
public class GUI {

    private boolean isClean;
    private boolean edit;
    private ArrayList<Table> tables = new ArrayList<>();
    private int currentTable;

    public GUI() {
        for (int i = 0; i < 16; i++) {
            tables.add(new Table());
        }
        int i = 0;
        for (Table table : tables) {
            table.setID(i);
            i++;
        }
    }

    public void menuBuilder(JTabbedPane pane) {
        JPanel wrapper = new JPanel();

        ImageIcon originalIcon = new ImageIcon(Main.class.getResource("/images/logo.png"));
        Image image = originalIcon.getImage(); // Get the Image object

        Image scaledImage = image.getScaledInstance(480, 100, Image.SCALE_SMOOTH);
        ImageIcon logoImage = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(logoImage);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JPanel container = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();

        container.setLayout(new GridLayout(1, 2, 20, 0));
        container.add(left);
        container.add(right);
        left.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        right.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        wrapper.add(logo);
        wrapper.add(container);
        ImageIcon menuIcon = new ImageIcon(Main.class.getResource("/images/square-menu.png"));
        pane.addTab("Menu Builder",menuIcon, wrapper);

        entryForm(left, right, new Item());
        menu(right, left);
    }

    public void entryForm(JPanel form, JPanel right, Item pItem) {
        edit = false;
        final int editID = pItem.getID();
        MenuBuilder builder = new MenuBuilder();
        form.setLayout(new GridLayout(0, 1));
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

        if (!pItem.getName().isEmpty()) {
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
                if (!edit) {
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

    public void orderEntryForm(JPanel form, JPanel orders, JPanel orderPage, JPanel container, ArrayList<OrderItem> menuItems, JLabel t, JButton tableBtn, JFrame frame) {
        form.removeAll();
        form.setLayout(new GridLayout(0, 1, 0, 5));
        JLabel title = new JLabel("Customer Information");
        title.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel nameLabel = new JLabel("Customer Name");
        JTextField nameField = new JTextField(40);
        JLabel nameErr = new JLabel("");

        JLabel emailLabel = new JLabel("Customer Email");
        JTextField emailField = new JTextField(40);
        JLabel emailErr = new JLabel("");

        JLabel descLabel = new JLabel("Special Instructions");
        JTextField descField = new JTextField(40);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(Color.decode("#7dbf73"));
        saveBtn.setForeground(Color.DARK_GRAY);

        JButton processBtn = new JButton("Process Order");
        processBtn.setBackground(Color.decode("#7dbf73"));
        processBtn.setForeground(Color.DARK_GRAY);
        processBtn.setMargin(new Insets(5, 5, 5, 5));

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.decode("#ff6e92"));
        backBtn.setForeground(Color.DARK_GRAY);

        JButton cancelBtn = new JButton("Cancel Order");
        cancelBtn.setEnabled(false);

        backBtn.addActionListener(e -> {
            orderPage.setVisible(false);
            orders.setVisible(true);
            t.setText("Place Orders");
        });

        cancelBtn.addActionListener(e -> {
            for (Table table : tables) {
                if (table.getID() == currentTable) {
                    table.setOrder(new Order(0, "", "", ""));
                    table.setStatus(false);
                    orderEntryForm(form, orders, orderPage, container, menuItems, t, tableBtn, frame);
                    tableBtn.setBackground(Color.white);
                    tableBtn.setForeground(Color.black);
                    cancelBtn.setEnabled(false);
                    form.revalidate();
                    form.repaint();
                    break;
                }
            }
        });

        for (Table table : tables) {
            if (currentTable == table.getID() && table.getStatus()) {
                nameField.setText(table.getOrder().getName());
                emailField.setText(table.getOrder().getEmail());
                descField.setText(table.getOrder().getSI());
                saveBtn.setText("Generate Bill");
                cancelBtn.setEnabled(true);

                menuItems.clear();
                for (OrderItem item : table.getOrder().getOrderItems()) {
                    menuItems.add(new OrderItem(item));
                }
            }
        }

        form.add(title);
        form.add(nameLabel);
        form.add(nameField);
        form.add(nameErr);
        form.add(emailLabel);
        form.add(emailField);
        form.add(emailErr);
        form.add(descLabel);
        form.add(descField);
        JLabel status = new JLabel();
        form.add(status);
        JPanel btns = new JPanel();
        btns.setLayout(new GridLayout(1, 2, 10, 0));
        btns.add(saveBtn);
        btns.add(cancelBtn);
        form.add(btns);
        form.add(backBtn);

        JLabel[] errors = {nameErr, emailErr, status};
        for (JLabel error : errors) {
            error.setForeground(Color.decode("#ff6e92"));
            error.setFont(new Font("Arial", Font.PLAIN, 11));
        }

        saveBtn.addActionListener(e -> {
            boolean isClean = true;

            if (nameField.getText().trim().isEmpty()) {
                nameErr.setText("Customer Name is required");
                isClean = false;
            } else {
                nameErr.setText("");
            }

            String emailText = emailField.getText().trim();
            if (emailText.isEmpty()) {
                emailErr.setText("Customer Email is required");
                isClean = false;
            } else if (!emailText.matches("^\\S+@\\S+\\.\\S+$")) {
                emailErr.setText("Invalid email format");
                isClean = false;
            } else {
                emailErr.setText("");
            }

            ArrayList<OrderItem> orderedItems = getOrderedItems(menuItems);

            if (orderedItems.isEmpty()) {
                status.setText("Select at least one item");
                isClean = false;
            } else {
                status.setText("");
            }

            if (isClean) {
                saveBtn.setText("Generate Bill");
                Order order = new Order(0, nameField.getText(), emailText, descField.getText());
                order.setOrderItems(orderedItems);
                for (Table table : tables) {
                    if (table.getID() == currentTable) {
                        table.setOrder(order);
                        table.setStatus(true);
                        if (tableBtn != null) {
                            tableBtn.setBackground(Color.RED);
                            tableBtn.setForeground(Color.WHITE);
                            cancelBtn.setEnabled(true);
                            Bill bill = new Bill(0, table.getOrder());
                            generateBill(bill, frame, processBtn, form, orders, orderPage, container, menuItems, t, tableBtn, table);
                        }
                    }
                }
                status.setText("Order placed");
            } else {
                saveBtn.setText("Save");
                System.out.println("Validation Errors Exist");
            }
        });
    }

    public void generateBill(Bill bill, JFrame frame, JButton processBtn, JPanel form, JPanel orders, JPanel orderPage, JPanel container, ArrayList<OrderItem> menuItems, JLabel t, JButton tableBtn, Table table) {
        Font billFont = new Font("Monospaced", Font.PLAIN, 14);
        JDialog billWindow = new JDialog(frame, "Bill for " + bill.getOrder().getName());
        billWindow.setLocationRelativeTo(null);
        billWindow.setSize(400, 550);
        billWindow.setVisible(true);
        billWindow.setLayout(new BorderLayout());
        JPanel billContainer = new JPanel();
        billContainer.setLayout(new GridLayout(0, 1));
        billContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel name = new JLabel(bill.getOrder().getName());
        JLabel email = new JLabel(bill.getOrder().getEmail());
        JLabel si = new JLabel(bill.getOrder().getSI());
        JLabel amount = new JLabel("Rs " + String.valueOf(bill.getTotalAmount()));
        JPanel head = new JPanel();
        head.setLayout(new GridLayout(1, 3));
        JLabel hName = new JLabel("Item");
        JLabel hSize = new JLabel("Size");
        JLabel hQty = new JLabel("Quantity");
        JLabel hPrice = new JLabel("Price");
        head.add(hName);
        head.add(hSize);
        head.add(hQty);
        head.add(hPrice);

        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(name);
        labels.add(email);
        labels.add(si);

        for (JLabel label : labels) {
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            billContainer.add(label);
            label.setFont(billFont);
            label.setMaximumSize(new Dimension(Short.MAX_VALUE, label.getPreferredSize().height));
        }
        name.setFont(new Font("Monospaced", Font.BOLD, 29));
        billContainer.add(new JLabel(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"));
        billContainer.add(head);
        JPanel rows = new JPanel();
        rows.setLayout(new GridLayout(0, 1));
        rows.setBackground(Color.red);
        for (OrderItem item : bill.getOrder().getOrderItems()) {
            JPanel row = new JPanel();
            row.setLayout(new GridLayout(1, 3));
            JLabel iName = new JLabel(item.getName());
            JLabel iSize = new JLabel(item.getSize());
            JLabel iQty = new JLabel(String.valueOf(item.getQuantity()));
            JLabel iPrice = new JLabel(String.valueOf(item.getPrice()));
            row.setMaximumSize(new Dimension(Short.MAX_VALUE, row.getPreferredSize().height));
            row.add(iName);
            row.add(iSize);
            row.add(iQty);
            row.add(iPrice);
            rows.add(row);
        }
        billContainer.add(rows);
        billContainer.add(new JLabel(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"));
        amount.setFont(new Font("Monospaced", Font.BOLD, 46));
        billContainer.add(amount);
        billWindow.add(billContainer, BorderLayout.NORTH);
        billWindow.add(processBtn, BorderLayout.SOUTH);
        processBtn.addActionListener(e -> {
            processOrder(table, bill);
            table.setOrder(new Order(0, "", "", ""));
            table.setStatus(false);
            orderEntryForm(form, orders, orderPage, container, menuItems, t, tableBtn, frame);
            orderPage.setVisible(false);
            orders.setVisible(true);
            tableBtn.setBackground(Color.white);
            tableBtn.setForeground(Color.black);
            billWindow.dispose();
            populateOrdersTable();
            
        });

    }

    public void processOrder(Table table, Bill bill) {
        OrderManager orderManager = new OrderManager();
        orderManager.storeOrder(bill, currentTable);
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
        itemRowHeading.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Fix height
        itemRowHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemRowHeading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemsContainer.add(itemRowHeading);

        // Data rows
        ArrayList<Item> items = builder.getItems();
        for (Item item : items) {
            JPanel itemRow = new JPanel(new GridLayout(1, 5));
            itemRow.add(new JLabel(item.getName()));
            itemRow.add(new JLabel(item.getCategory()));
            itemRow.add(new JLabel(String.valueOf(item.getPrice())));
            itemRow.add(new JLabel(item.getSize()));
            itemRow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            JButton delete = new JButton("");
            JButton edit = new JButton("");
            ImageIcon deleteIcon = new ImageIcon(Main.class.getResource("/images/delete.png"));
            JLabel dIcon = new JLabel(deleteIcon);
            dIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            delete.add(dIcon);
            ImageIcon editIcon = new ImageIcon(Main.class.getResource("/images/square-pen.png"));
            JLabel eIcon = new JLabel(editIcon);
            eIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            edit.add(eIcon);
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
            itemRow.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
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

    public void orderMenu(JPanel container, ArrayList<OrderItem> items) {
        container.removeAll();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Menu");
        JLabel refreshIcon = new JLabel(new ImageIcon(Main.class.getResource("/images/refresh.png")));
        JButton refresh = new JButton("");
        refresh.add(refreshIcon);
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.setAlignmentX(Component.LEFT_ALIGNMENT);
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        title.setFont(new Font("Arial", Font.BOLD, 16));

        top.add(title);
        top.add(Box.createHorizontalGlue());
        top.add(refresh);
        top.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        container.add(top);

        JPanel itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel itemRowHeading = new JPanel(new GridLayout(1, 6));
        itemRowHeading.setBackground(Color.LIGHT_GRAY);
        itemRowHeading.add(new JLabel("Name"));
        itemRowHeading.add(new JLabel("Category"));
        itemRowHeading.add(new JLabel("Price"));
        itemRowHeading.add(new JLabel("Size"));
        itemRowHeading.add(new JLabel("Quantity"));
        itemRowHeading.add(new JLabel("Actions"));
        itemRowHeading.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        itemRowHeading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemsContainer.add(itemRowHeading);

        for (OrderItem item : items) {
            JPanel itemRow = new JPanel(new GridLayout(1, 6));
            itemRow.add(new JLabel(item.getName()));
            itemRow.add(new JLabel(item.getCategory()));
            itemRow.add(new JLabel(String.valueOf(item.getPrice())));
            itemRow.add(new JLabel(item.getSize()));

            JLabel qty = new JLabel(String.valueOf(item.getQuantity()));
            itemRow.add(qty);

            JButton plus = new JButton("+");
            JButton min = new JButton("-");

            plus.addActionListener(e -> {
                item.incrementQuantity();
                qty.setText(String.valueOf(item.getQuantity()));
            });

            min.addActionListener(e -> {
                if (item.getQuantity() > 0) {
                    item.decrementQuantity();
                    qty.setText(String.valueOf(item.getQuantity()));
                }
            });

            JPanel actions = new JPanel();
            actions.add(plus);
            actions.add(min);
            itemRow.add(actions);

            itemRow.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
            itemRow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            itemsContainer.add(itemRow);
        }

        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(scrollPane);

        container.revalidate();
        container.repaint();

        refresh.addActionListener(e -> {
            ArrayList<OrderItem> updatedItems = new OrderManager().getOrderItems();
            items.clear();
            for (OrderItem item : updatedItems) {
                items.add(new OrderItem(item));
            }
            orderMenu(container, items);
        });
    }

    public void ordersPage(JTabbedPane pane, JFrame frame) {
        JPanel processOrderPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JPanel orders = new JPanel();
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Place Orders");

        ImageIcon originalIcon = new ImageIcon(Main.class.getResource("/images/logo.png"));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(480, 100, Image.SCALE_SMOOTH);
        ImageIcon logoImage = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(logoImage);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        orders.add(logo);
        orders.add(title);

        JPanel container = new JPanel(new GridLayout(4, 4, 5, 5));
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        JPanel menu = new JPanel();

        ArrayList<OrderItem> sharedMenuItems = new OrderManager().getOrderItems(); // class-level or local

        processOrderPanel.setVisible(false);
        JButton[] tableButtons = new JButton[tables.size()];

        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            JButton tableBtn = new JButton("Table " + table.getID());

            if (table.getStatus()) {
                tableBtn.setBackground(Color.RED);
                tableBtn.setForeground(Color.WHITE);
            }

            final int tableId = table.getID();
            final JButton currentBtn = tableBtn;

            tableBtn.addActionListener(e -> {
                currentTable = tableId;

                ArrayList<OrderItem> clonedItems = new ArrayList<>();
                for (OrderItem item : new OrderManager().getOrderItems()) {
                    clonedItems.add(new OrderItem(item));
                }

                container.setVisible(false);
                processOrderPanel.setVisible(true);
                title.setText("Order for " + currentBtn.getText());

                orderEntryForm(form, container, processOrderPanel, menu, clonedItems, title, currentBtn, frame);
                orderMenu(menu, clonedItems); // always use fresh clone
            });

            container.add(tableBtn);
            tableButtons[i] = tableBtn;
        }

        // Initial render
        orderEntryForm(form, container, processOrderPanel, menu, new ArrayList<>(), title, null, frame);
        orderMenu(menu, new ArrayList<>());

        container.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        processOrderPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        menu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        processOrderPanel.add(form);
        processOrderPanel.add(menu);

        orders.add(container);
        orders.add(processOrderPanel);
        ImageIcon ordersIcon = new ImageIcon(Main.class.getResource("/images/orders.png"));
        pane.addTab("Orders", ordersIcon, orders);
        insightsPanel(pane);
    }

    public ArrayList<OrderItem> getOrderedItems(ArrayList<OrderItem> items) {
        ArrayList<OrderItem> newItems = new ArrayList<>();
        for (OrderItem item : items) {
            if (item.getQuantity() > 0) {
                newItems.add(item);
            }
        }
        return newItems;
    }

    public static DefaultTableModel reportsModel;

    public void insightsPanel(JTabbedPane pane) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel wrapper = new JPanel();
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        ImageIcon panelIcon = new ImageIcon(Main.class.getResource("/images/chart.png"));

        pane.addTab("Reports & Insights Panel",panelIcon, container);
        ImageIcon originalIcon = new ImageIcon(Main.class.getResource("/images/logo.png"));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(480, 100, Image.SCALE_SMOOTH);
        ImageIcon logoImage = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(logoImage);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(logo);
        wrapper.setLayout(new GridLayout(1, 2, 10, 0));
        JPanel reports = new JPanel();
        JPanel insights = new JPanel();
        reports.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        insights.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JLabel reportsTitle =new JLabel("Orders Report");
        JLabel insightsTitle =new JLabel("insights");
        
        reportsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        insightsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        reportsTitle.setFont(new Font("Arial", Font.BOLD, 21));
        insightsTitle.setFont(new Font("Arial", Font.BOLD, 21));
        
        reports.add(reportsTitle);
        insights.add(insightsTitle);
        reports.setLayout(new BoxLayout(reports, BoxLayout.Y_AXIS));
        insights.setLayout(new BoxLayout(insights, BoxLayout.Y_AXIS));

        String[] cols = {"Order ID", "Customer Name", "Bill Amount", "Date"};
        reportsModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(reportsModel);
        JScrollPane scrollPane = new JScrollPane(table);
        reports.add(scrollPane);
        populateOrdersTable();

        wrapper.add(reports);
        wrapper.add(insights);
        container.add(wrapper);
        insigths(insights);
    }

    public void insigths(JPanel insights) {
        insights.removeAll();
        OrderManager orderManager = new OrderManager();
        insights.setLayout(new BoxLayout(insights, BoxLayout.Y_AXIS));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(2, 2, 5, 5));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel totalOrdersToday = new JPanel();
        totalOrdersToday.setLayout(new BoxLayout(totalOrdersToday, BoxLayout.Y_AXIS));
        JLabel ordersTitleToday = new JLabel("Orders Placed Today");
        JLabel ordersInfoToday = new JLabel();

        JPanel totalAmountToday = new JPanel();
        totalAmountToday.setLayout(new BoxLayout(totalAmountToday, BoxLayout.Y_AXIS));
        JLabel amountTitleToday = new JLabel("Amount Generated Today");
        JLabel amountInfoToday = new JLabel();

        // Monthly Panels
        JPanel totalOrdersMonth = new JPanel();
        totalOrdersMonth.setLayout(new BoxLayout(totalOrdersMonth, BoxLayout.Y_AXIS));
        JLabel ordersTitleMonth = new JLabel("Orders This Month");
        JLabel ordersInfoMonth = new JLabel();

        JPanel totalAmountMonth = new JPanel();
        totalAmountMonth.setLayout(new BoxLayout(totalAmountMonth, BoxLayout.Y_AXIS));
        JLabel amountTitleMonth = new JLabel("Amount Generated This Month");
        JLabel amountInfoMonth = new JLabel();

        // Counters
        int totalOrdersTodayN = 0;
        double totalAmountTodayN = 0;
        int totalOrdersMonthN = 0;
        double totalAmountMonthN = 0;

        LocalDate today = LocalDate.now();
        String todayStr = today.toString();
        String currentMonth = todayStr.substring(0, 7);

        for (Bill bill : orderManager.getOrders()) {
            String billDateStr = bill.getDate();
            if (billDateStr.equals(todayStr)) {
                totalOrdersTodayN++;
                totalAmountTodayN += bill.getTotalAmount();
            }
            if (billDateStr.substring(0, 7).equals(currentMonth)) {
                totalOrdersMonthN++;
                totalAmountMonthN += bill.getTotalAmount();
            }
        }

        ordersInfoToday.setText(String.valueOf(totalOrdersTodayN));
        amountInfoToday.setText(String.format("%.2f", totalAmountTodayN));
        ordersInfoMonth.setText(String.valueOf(totalOrdersMonthN));
        amountInfoMonth.setText(String.format("%.2f", totalAmountMonthN));

        Font bigFont = new Font("Monospaced", Font.BOLD, 48);
        ordersInfoToday.setFont(bigFont);
        amountInfoToday.setFont(bigFont);
        ordersInfoMonth.setFont(bigFont);
        amountInfoMonth.setFont(bigFont);

        totalOrdersToday.add(ordersTitleToday);
        totalOrdersToday.add(ordersInfoToday);

        totalAmountToday.add(amountTitleToday);
        totalAmountToday.add(amountInfoToday);

        totalOrdersMonth.add(ordersTitleMonth);
        totalOrdersMonth.add(ordersInfoMonth);

        totalAmountMonth.add(amountTitleMonth);
        totalAmountMonth.add(amountInfoMonth);

        container.add(totalOrdersToday);
        container.add(totalAmountToday);
        container.add(totalOrdersMonth);
        container.add(totalAmountMonth);

        insights.add(container);
    }

    public void populateOrdersTable() {
        OrderManager orderManager = new OrderManager();
        reportsModel.setRowCount(0);
        for (Bill bill : orderManager.getOrders()) {
            reportsModel.addRow(new Object[]{bill.getID(), bill.getOrder().getName(), bill.getTotalAmount(), bill.getDate()});
            if (bill.getDate().equals(LocalDate.now().toString())) {
                System.out.println(bill.getTotalAmount());
            }
        }
    }
}
