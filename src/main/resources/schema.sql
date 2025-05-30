-- Create purchased table with customer_id
CREATE TABLE IF NOT EXISTS purchased (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(255),
    customer_id BIGINT,
    quantity INT,
    total DOUBLE,
    date DATE
);

-- Create trigger for total price calculation
DELIMITER //

CREATE TRIGGER calculate_purchased_total
BEFORE INSERT ON purchased
FOR EACH ROW
BEGIN
    SET NEW.total = (
        SELECT price * NEW.quantity
        FROM product
        WHERE code = NEW.product_code
    );
END//

DELIMITER ;
