CREATE TABLE netology.CUSTOMERS (
	id serial PRIMARY KEY, 
	name varchar(255), 
	surname varchar(255), 
	age integer, 
	phone_number varchar(12)
);

CREATE TABLE netology.ORDERS (
	id serial PRIMARY KEY, 
	date date, 
	customer_id integer, 
	product_name varchar(255), 
	amount numeric,
	FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id)
);
