CREATE DATABASE shopapp;

USE shopapp;

CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    PASSWORD VARCHAR(100) NOT NULL DEFAULT '',
    create_at DATETIME,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0,
    role_id INT,
    FOREIGN KEY role_id REFERENCES roles(id)
) CREATE TABLE roles(
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
) CREATE TABLE tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL token_type VARCHAR (50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users (id)
) CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Tên nhà social network',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'Email tài khoản',
    name VARCHAR(100) NOT NULL COMMENT 'Tên người dùng',
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id)
) CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Tên danh mục'
) CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) COMMENT 'Tên sản phẩm',
    price float NOT NULL CHECK (price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT,
    create_at DATETIME,
    updated_at DATETIME,
    category_id int,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE product_images(
	id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT 	 fk_product_images_product_id
		FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
)

 CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR (200) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    note VARCHAR(200) DEFAULT '',
    order_date DATETIME CURRENT_TIMESTAMP,
    STATUS VARCHAR(20) total_money FLOAT CHECK(total_money >= 0),
    shipping_method VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date DATE,
    tracking_number VARCHAR(100),
    payment_method VARCHAR(100),
    active TINYINT(1),
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id)

)
ALTER TABLE
    order
MODIFY
    COLUMN STATUS ENUM(
        'pending',
        'processing',
        'shipped',
        'delivered',
        'cancelled'
    ) COMMENT 'Trạng thái đơn hàng';

CREATE TABLE order_details(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id int,
    product_id int,
    price FLOAT CHECK(price >= 0),
    number_of_products INT CHECK (number_of_products > 0),
    color VARCHAR(20) DEFAULT '' FOREIGN KEY order_id REFERENCES orders(id),
    FOREIGN KEY product_id REFERENCES products(id)
)