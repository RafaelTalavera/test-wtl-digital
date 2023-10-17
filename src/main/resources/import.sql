-- Insertar datos en la tabla 'products'
INSERT INTO products (isbn, description, brand, stock, price) VALUES( 1444777,'LCD 29 Pulgadas', 'Panasonic', 7 , 259);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781234567890, 'Smartphone Samsung Galaxy S21', 'Samsung', 25, 899.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780451524935, 'Laptop HP Pavilion', 'HP', 10, 699.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780061120084, 'Tablet Apple iPad Pro', 'Apple', 15, 799.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780132350884, 'Smart TV LG 55 Pulgadas', 'LG', 5, 599.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780321776419, 'PlayStation 5', 'Sony', 20, 499.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780201835953, 'Xbox Series X', 'Microsoft', 18, 499.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780735619678, 'Bluetooth Speaker JBL Flip 5', 'JBL', 30, 89.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780134757065, 'Wireless Mouse Logitech MX Master 3', 'Logitech', 12, 79.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781118008188, 'Wireless Keyboard Logitech K780', 'Logitech', 15, 49.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780596007126, 'Coffee Maker Keurig K-Elite', 'Keurig', 8, 129.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781451648546, 'Blender Ninja Professional BL610', 'Ninja', 10, 89.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781408855652, 'eBook Reader Amazon Kindle Paperwhite', 'Amazon', 22, 129.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780545586177, 'Noise-Canceling Headphones Bose QuietComfort 35 II', 'Bose', 15, 199.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780062315007, 'Digital Camera Canon EOS Rebel T7', 'Canon', 7, 599.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780062489561, 'External Hard Drive WD My Passport', 'WD', 10, 79.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781250217780, 'Fitness Tracker Fitbit Charge 4', 'Fitbit', 14, 129.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781506712602, 'Wireless Earbuds Apple AirPods Pro', 'Apple', 20, 199.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9781787292533, 'Smart Watch Samsung Galaxy Watch 4', 'Samsung', 15, 249.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780316119690, 'Coffee Grinder Baratza Encore', 'Baratza', 9, 139.99);
INSERT INTO products (isbn, description, brand, stock, price) VALUES (9780451530967, 'Refrigerator Whirlpool WRB322DMBM', 'Whirlpool', 6, 999.99);



-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Orden Sales prueba 1', 1, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 1, 1);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 1, 4);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 1, 5);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 1, 7);

-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Orden de prueba 2', 2, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (3, 2, 3);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 2, 6);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 2, 8);

-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Venta especial', 3, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (5, 3, 2);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (4, 3, 4);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 3, 7);

-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Venta express', 1, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 4, 1);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 4, 5);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (3, 4, 6);

-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Venta anual', 5, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (4, 5, 3);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 5, 4);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (6, 5, 7);

-- Insertar una orden de venta en sales_orders
INSERT INTO sales_orders (description, customer_id, create_at) VALUES ('Venta de verano', 2, NOW());

-- Insertar registros en items_sales que hacen referencia a la orden de venta creada
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (2, 6, 2);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (3, 6, 3);
INSERT INTO items_sales (quantity, sales_order_id, product_id) VALUES (1, 6, 5);