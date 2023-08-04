INSERT INTO customer (id, name) VALUES (1, 'user1');
INSERT INTO customer (id, name) VALUES (2, 'user2');
INSERT INTO customer (id, name) VALUES (3, 'user3');
INSERT INTO customer (id, name) VALUES (4, 'user4');
INSERT INTO customer (id, name) VALUES (5, 'user5');
INSERT INTO customer (id, name) VALUES (6, 'user6');
INSERT INTO customer (id, name) VALUES (7, 'user7');
INSERT INTO customer (id, name) VALUES (8, 'user8');


INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (1, 170, {ts '2022-12-22 00:00:00.00'}, 1);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (2, 10, {ts '2023-07-01 00:00:00.00'}, 1);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (3, 400, {ts '2023-08-01 00:00:00.00'}, 1);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (4, 10000, {ts '2023-01-23 00:00:00.00'}, 2);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (5, 290, {ts '2023-02-23 00:00:00.00'}, 2);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (6, 100, {ts '2023-03-23 00:00:00.00'}, 2);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (7, 30, {ts '2023-04-23 00:00:00.00'}, 2);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (8, 50, {ts '2023-05-23 00:00:00.00'}, 2);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (9, 120, {ts '2023-06-23 00:00:00.00'}, 2);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (10, 1, {ts '2023-07-23 00:00:00.00'}, 3);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (11, 101, {ts '2023-08-1 00:00:00.00'}, 4);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (12, 51, {ts '2023-08-02 00:00:00.00'}, 4);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (13, 0, {ts '2023-08-03 00:00:00.00'}, 4);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (14, 49, {ts '2023-06-01 00:00:00.00'}, 5);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (15, 99, {ts '2023-06-21 00:00:00.00'}, 5);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (16, 2023, {ts '2023-07-13 00:00:00.00'}, 5);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (17, 379502, {ts '2023-07-13 00:00:00.00'}, 5);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (18, 20, {ts '2023-07-14 00:00:00.00'}, 5);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (19, 3, {ts '2023-08-02 00:00:00.00'}, 5);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (20, 66, {ts '2022-10-01 00:00:00.00'}, 6);
INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (21, 99, {ts '2022-11-01 00:00:00.00'}, 6);

INSERT INTO transaction (id, transaction_value, transaction_time, customer_id) VALUES (22, 100, {ts '2023-04-20 00:00:00.00'}, 7);