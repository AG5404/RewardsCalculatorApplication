INSERT INTO customers (customer_name, customer_email) VALUES ('test1', 'test1@example.com');
INSERT INTO customers (customer_name, customer_email) VALUES ('test2', 'test2@example.com');
INSERT INTO customers (customer_name, customer_email) VALUES ('test3', 'test3@example.com');

INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (1,'2026-04-12',120);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (1,'2026-02-01',85);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (1,'2026-03-04',50);



INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (2,'2026-07-05',400);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (2,'2026-08-01',10);


INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (3,'2026-09-04',-190);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (3,'2026-06-12',50);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (3,'2026-08-30',275);
INSERT INTO transactions(customer_id,transaction_date, amount) VALUES (3,'2026-07-20',0);