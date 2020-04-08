DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS users;

CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(40) NOT NULL,
                          department VARCHAR(40),
                          description VARCHAR(200)
);

CREATE TABLE supplier (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(40) NOT NULL,
                          description VARCHAR(200)
);

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(40) NOT NULL,
                         image VARCHAR(40),
                         description VARCHAR(200) NOT NULL,
                         default_price FLOAT,
                         default_currency VARCHAR(40) NOT NULL,
                         product_cat INTEGER REFERENCES category(id),
                         supplier INTEGER REFERENCES supplier(id)
);

CREATE TABLE users (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(20) NOT NULL,
                          psw VARCHAR(40) NOT NULL,
                          email VARCHAR(40) NOT NULL,
                          image VARCHAR(40),
                          isAdmin BOOLEAN DEFAULT False
);

INSERT INTO category (name, department, description) VALUES
('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display'),
('Phone', 'Hardware', 'Smartphones are a class of mobile phones and of multi-purpose mobile computing devices')
;

INSERT INTO supplier (name, description) VALUES
('Amazon', 'Digital content and services'),
('Lenovo', 'Computers'),
('Apple', 'Smartphone and tablet producer')
;

INSERT INTO product (name, image, description, default_price, default_currency, product_cat, supplier) VALUES
('Amazon Fire', 'product_1', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support', 49, 'USD', 1, 1),
('Lenovo IdeaPad Miix 700', 'product_2', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand', 479, 'USD', 1, 1),
('Amazon Fire HD 8', 'product_3', 'Amazons latest Fire HD 8 tablet is a great value for media consumption', 89, 'USD', 1, 2),
('iPhone XR', 'product_4', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support', 749, 'USD', 2, 3),
('Apple 10.5'' iPad Air', 'product_5', 'Updated from the same one you know and love, features a few improvements to provide you with a better experience', 499, 'USD', 1, 3)
;

INSERT INTO users (name, psw, email, image) VALUES
('Marek', 'marek1', 'marek@marek.pl', 'marek1'),
('Admin', 'admin', 'admin@admin.com', 'admin');