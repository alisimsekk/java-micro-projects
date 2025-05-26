-- Categories
INSERT INTO category (name, description, created_at, updated_at) VALUES 
('Electronics', 'Electronic devices and gadgets', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Clothing', 'Apparel and fashion items', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Books', 'Books and publications', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Home & Kitchen', 'Home appliances and kitchen supplies', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Sports', 'Sports equipment and gear', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Products in Electronics
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Smartphone', 'Latest model smartphone with advanced features', 699.99, 50, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Laptop', 'High-performance laptop for professional use', 1299.99, 25, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Headphones', 'Noise-cancelling wireless headphones', 149.99, 100, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Smartwatch', 'Fitness and health tracking smartwatch', 299.99, 35, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Tablet', 'Portable tablet for entertainment and work', 499.99, 40, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Products in Clothing
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('T-shirt', 'Cotton t-shirt available in various colors', 19.99, 200, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Jeans', 'Denim jeans with modern fit', 49.99, 150, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Dress', 'Elegant dress for special occasions', 89.99, 75, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Jacket', 'Waterproof jacket for outdoor activities', 129.99, 60, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Shoes', 'Comfortable walking shoes', 79.99, 100, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Products in Books
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Programming Guide', 'Comprehensive guide to modern programming', 39.99, 120, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Science Fiction Novel', 'Bestselling science fiction story', 24.99, 85, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Cookbook', 'Collection of gourmet recipes', 29.99, 70, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('History Book', 'Detailed account of world history', 34.99, 55, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Self-Help Book', 'Guide to personal development', 19.99, 95, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Products in Home & Kitchen
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Coffee Maker', 'Programmable coffee maker for home use', 89.99, 40, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Blender', 'High-speed blender for smoothies and more', 69.99, 30, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Toaster', '4-slice toaster with multiple settings', 49.99, 45, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Vacuum Cleaner', 'Powerful vacuum cleaner for all surfaces', 199.99, 25, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Microwave Oven', 'Compact microwave oven for quick heating', 149.99, 20, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Products in Sports
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Basketball', 'Official size basketball', 29.99, 60, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Yoga Mat', 'Non-slip yoga mat for exercise', 24.99, 80, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Dumbbells', 'Set of adjustable dumbbells', 149.99, 35, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Running Shoes', 'Lightweight shoes for running', 99.99, 70, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Tennis Racket', 'Professional tennis racket', 119.99, 25, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Add some budget products for testing price classification
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('USB Cable', 'Basic USB charging cable', 9.99, 200, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Phone Case', 'Protective case for smartphones', 14.99, 300, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Socks', 'Pack of 5 cotton socks', 12.99, 250, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Notepad', 'Simple lined notepad', 4.99, 400, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Kitchen Towel', 'Set of 3 kitchen towels', 7.99, 150, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Water Bottle', 'Basic plastic water bottle', 6.99, 180, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Add some premium products for testing price classification
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Gaming Console', 'Latest generation gaming console', 499.99, 15, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Designer Coat', 'Premium designer winter coat', 499.99, 10, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Collectors Edition', 'Limited collectors edition book set', 399.99, 5, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Stand Mixer', 'Professional kitchen stand mixer', 399.99, 8, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Bicycle', 'Mountain bike with advanced features', 599.99, 7, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Add some luxury products for testing price classification
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('High-end Camera', 'Professional photography camera', 2499.99, 5, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Designer Handbag', 'Luxury designer handbag', 1299.99, 3, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Home Theater System', 'Complete home theater setup', 1999.99, 2, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Golf Club Set', 'Premium golf clubs with bag', 1499.99, 4, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Add some products with low stock for testing
INSERT INTO product (name, description, price, stock, category_id, created_at, updated_at) VALUES 
('Limited Edition Watch', 'Collectible limited edition watch', 599.99, 3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Vintage Vinyl Record', 'Rare vintage vinyl record', 89.99, 2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Premium Coffee Beans', 'Exotic premium coffee beans', 39.99, 5, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Signed Jersey', 'Sports jersey with athlete signature', 299.99, 1, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()); 