SELECT product_name FROM orders o JOIN customers c ON o.customer_id = c.id WHERE c.name = :firstName;
