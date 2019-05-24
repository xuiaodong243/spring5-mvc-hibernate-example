--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;



SET default_tablespace = '';

SET default_with_oids = false;

---
--- drop tables
---

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS employees;

CREATE TABLE customers (
    customer_id int NOT NULL,
    name character varying(40) NOT NULL,
    description character varying(200),
    address character varying(60),
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);

CREATE TABLE employees (
    employee_id int NOT NULL,
    name character varying(40) NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);

CREATE TABLE orders (
    order_id int NOT NULL,
	product_id int NOT NULL,
	customer_id int NOT NULL,
	employee_id int NOT NULL,
	unit_price real,
	order_dt timestamp without time zone,
	status character varying(10) NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);

CREATE TABLE products (
    product_id int NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(200) NOT NULL,
	unit_price real,
	supplier_id int NOT NULL,
	category_id int NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);

CREATE TABLE suppliers (
    supplier_id int NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(200) NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);

CREATE TABLE categories (
    category_id int NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(200) NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone
);
ALTER TABLE ONLY categories
    ADD CONSTRAINT pk_categories PRIMARY KEY (category_id);
ALTER TABLE ONLY customers
    ADD CONSTRAINT pk_customers PRIMARY KEY (customer_id);
ALTER TABLE ONLY employees
    ADD CONSTRAINT pk_employees PRIMARY KEY (employee_id);
ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_orders PRIMARY KEY (order_id);
ALTER TABLE ONLY products
    ADD CONSTRAINT pk_products PRIMARY KEY (product_id);
ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_suppliers PRIMARY KEY (supplier_id);


INSERT INTO categories VALUES (1, 'Beverages', 'Soft drinks, coffees, teas, beers, and ales', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (2, 'Condiments', 'Sweet and savory sauces, relishes, spreads, and seasonings', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (3, 'Confections', 'Desserts, candies, and sweet breads', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (4, 'Dairy Products', 'Cheeses', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (5, 'Grains/Cereals', 'Breads, crackers, pasta, and cereal', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (6, 'Meat/Poultry', 'Prepared meats', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (7, 'Produce', 'Dried fruit and bean curd', 'admin',now(),'admin',now());
INSERT INTO categories VALUES (8, 'Seafood', 'Seaweed and fish', 'admin',now(),'admin',now());


INSERT INTO customers VALUES (1, 'Alfreds Futterkiste','Sales Representative', 'Obere Str. 57', 'admin',now(),'admin',now());
INSERT INTO customers VALUES (2, 'Ana Trujillo Emparedados y helados', 'Ana Trujillo', 'Avda. de la Constitución 2222', 'admin',now(),'admin',now());
INSERT INTO customers VALUES (3, 'Antonio Moreno Taquería', 'Antonio Moreno', 'Mataderos  2312','admin',now(),'admin',now());
INSERT INTO customers VALUES (4, 'Around the Horn', 'Sales Representative', '120 Hanover Sq.', 'admin',now(),'admin',now());
INSERT INTO customers VALUES (5, 'Berglunds snabbköp', 'Christina Berglund', 'Berguvsvägen  8', 'admin',now(),'admin',now());
INSERT INTO customers VALUES (6, 'Blauer See Delikatessen', 'Sales Representative', 'Forsterstr. 57','admin',now(),'admin',now());
INSERT INTO customers VALUES (7, 'Blondesddsl père et fils', 'Marketing Manager', '24, place Kléber','admin',now(),'admin',now());
INSERT INTO customers VALUES (8, 'Bólido Comidas preparadas', 'Martín Sommer', 'C/ Araquil, 67','admin',now(),'admin',now());


INSERT INTO employees VALUES (1, 'Nancy','admin',now(),'admin',now());
INSERT INTO employees VALUES (2, 'Margaret','admin',now(),'admin',now());


INSERT INTO products VALUES (1, 'Chai','10 boxes x 30 bags', 18, 1, 1,'admin',now(),'admin',now());
INSERT INTO products VALUES (2, 'Chang','24 - 12 oz bottles', 19, 1, 1, 'admin',now(),'admin',now());
INSERT INTO products VALUES (3, 'Aniseed Syrup','12 - 550 ml bottles', 10, 1, 1,'admin',now(),'admin',now());
INSERT INTO products VALUES (4, 'Chef Anton''s Cajun Seasoning','48 - 6 oz jars', 22, 1, 1, 'admin',now(),'admin',now());

INSERT INTO suppliers VALUES (1, 'Exotic Liquids', 'Charlotte Cooper','admin',now(),'admin',now());
INSERT INTO suppliers VALUES (2, 'New Orleans Cajun Delights', 'Shelley Burke', 'admin',now(),'admin',now());


CREATE TABLE products_history (
  h_id bigint NOT NULL,
  product_id int NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(200) NOT NULL,
	unit_price real,
	supplier_id int NOT NULL,
	category_id int NOT NULL,
    lastupd_by character varying(20),
    lastupd_dt timestamp without time zone,
    create_by character varying(20),
    create_dt timestamp without time zone,
    insert_dt timestamp without time zone
);
ALTER TABLE ONLY products_history
    ADD CONSTRAINT pk_products_history PRIMARY KEY (h_id);

CREATE SEQUENCE products_history_seq;

CREATE OR REPLACE FUNCTION process_product_audit()
RETURNS TRIGGER AS
$product_audit$
   DECLARE
     audit_table_name text := 'products_history';
   BEGIN
   EXECUTE FORMAT('INSERT INTO %1$I
          SELECT NEXTVAL(''products_history_seq''),
            ($1).*,now()',
          audit_table_name)
              USING OLD;
           RETURN NEW;
   END;
$product_audit$
LANGUAGE plpgsql VOLATILE
COST 100;

CREATE TRIGGER product_demo_changes
Before UPDATE
ON products
FOR EACH ROW
EXECUTE PROCEDURE process_product_audit();




CREATE TABLE audit_log (
  audit_id bigint NOT NULL,
	table_name character varying(50) NOT NULL,
	column_name character varying(200) NOT NULL,
	row_id bigint,
    change_by character varying(20),
    lastupd_dt timestamp without time zone,
    old_value character varying(200),
    new_value character varying(200)
);
ALTER TABLE ONLY audit_log
    ADD CONSTRAINT pk_audit_log PRIMARY KEY (audit_id);
CREATE SEQUENCE audit_log_seq;
ALTER TABLE audit_log ALTER audit_id SET DEFAULT NEXTVAL('audit_log_seq');
alter table audit_log alter column audit_id drop default;

CREATE SEQUENCE demo_product_seq;
