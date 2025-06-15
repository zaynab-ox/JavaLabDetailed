package DB;

public class DatabaseSetup {

    public static final String CREATE_CATEGORY_TABLE = """
        CREATE TABLE category (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(100)
        );
    """;

    public static final String CREATE_MENU_TABLE = """
        CREATE TABLE menu (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255),
            price DOUBLE,
            size VARCHAR(50),
            category_id INT,
            FOREIGN KEY (category_id) REFERENCES category(id)
        );
    """;

    public static final String CREATE_MENU_LOG_TABLE = """
        CREATE TABLE menu_log (
            id INT,
            name VARCHAR(255),
            price DOUBLE,
            category VARCHAR(100),
            size VARCHAR(50),
            deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    public static final String CREATE_DELETE_TRIGGER = """
        DELIMITER $$
        CREATE TRIGGER before_delete_menu
        BEFORE DELETE ON menu
        FOR EACH ROW
        BEGIN
            INSERT INTO menu_log (id, name, price, category, size)
            VALUES (OLD.id, OLD.name, OLD.price, (SELECT name FROM category WHERE id = OLD.category_id), OLD.size);
        END$$
        DELIMITER ;
    """;

    public static final String CREATE_ADD_ITEM_PROCEDURE = """
        DELIMITER $$
        CREATE PROCEDURE add_menu_item(IN p_name VARCHAR(255), IN p_price DOUBLE, IN p_category VARCHAR(100), IN p_size VARCHAR(50))
        BEGIN
            DECLARE cat_id INT;
            SELECT id INTO cat_id FROM category WHERE name = p_category LIMIT 1;
            INSERT INTO menu(name, price, size, category_id) VALUES (p_name, p_price, p_size, cat_id);
        END$$
        DELIMITER ;
    """;

    public static final String CREATE_UPDATE_ITEM_PROCEDURE = """
        DELIMITER $$
        CREATE PROCEDURE update_menu_item(IN p_id INT, IN p_name VARCHAR(255), IN p_price DOUBLE, IN p_category VARCHAR(100), IN p_size VARCHAR(50))
        BEGIN
            DECLARE cat_id INT;
            SELECT id INTO cat_id FROM category WHERE name = p_category LIMIT 1;
            UPDATE menu SET name = p_name, price = p_price, size = p_size, category_id = cat_id WHERE id = p_id;
        END$$
        DELIMITER ;
    """;

    public static final String CREATE_DELETE_ITEM_PROCEDURE = """
        DELIMITER $$
        CREATE PROCEDURE delete_menu_item(IN p_id INT)
        BEGIN
            DELETE FROM menu WHERE id = p_id;
        END$$
        DELIMITER ;
    """;
}
