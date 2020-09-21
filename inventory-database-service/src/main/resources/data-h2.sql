-- Dummy data for User Table
INSERT INTO user (user_id, password, username, role) VALUES (100001,'123456', 'gagan', 'Admin');
INSERT INTO user (user_id, password, username, role) VALUES (100002,'123456', 'palak', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100003,'123456', 'pragya', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100004,'123456', 'rushikesh', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100005,'123456', 'sulekha', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100006,'123456', 'raghav', 'User');

-- Dummy data for User detail
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100001, 'Administrator', '1999-05-14', 'singh.gagandeep3911@gmail.com', 'Male', '8419969059', 'Default Question', 'answer');
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100002, 'Sales Manager', '1997-12-12', 'palak@mail.com', 'Female', '9876543210', 'Default Question', 'answer');
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100003, 'Marketing Lead', '1998-04-04', 'pragya@mail.com', 'Female', '8877669059', 'Default Question', 'answer');
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100004, 'Sales Team', '1995-05-11', 'rushikesh@mail.com', 'Male', '3443312345', 'Default Question', 'answer');
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100005, 'Sales Team', '1999-01-19', 'sulekha@mail.com', 'Female', '7576788679', 'Default Question', 'answer');
INSERT INTO user_details (user_details_id, designation, dob, email_id, gender, phone_no, security_question, security_answer) VALUES (100006, 'Sales Team', '1990-03-12', 'raghav@mail.com', 'Male', '9966553322', 'Default Question', 'answer');

-- Dummy data for Address
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100001,'Union Street','Seattle','98106','Washington');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100002,'Stonepot Road','Newark','07102','New Jersey');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100003,'Adams Drive','Houston','77002','Texas');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100004,'Illinois Avenue','Tigard','97223','Oregon');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100005,'Hog Camp Road','La Grange','60525','Illinois');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100006,'West Fork Drive','CLEVELAND','44113','Ohio');
-- MYSQL sequence
-- UPDATE user_id_sequence SET next_val = 100021;
-- H2 sequence
ALTER sequence user_id_sequence restart with 100007;
