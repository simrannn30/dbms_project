drop database if exists dear_diet;
create database dear_diet;
use dear_diet;

create table Employees(
E_ID int,
E_Name varchar(55), 
Designation varchar(55), 
Contact varchar(12), 
Email varchar(55),
Password varchar(10),
Address varchar(255),
Salary bigint,
primary key(E_ID)
);
create table Manufacturers(
M_ID varchar(20), 
M_Name varchar(55), 
Contact varchar(12), 
Address varchar(255), 
POC int, 
primary key (M_ID),
constraint foreign key(POC) references Employees(E_ID) on delete cascade
);
create table Orders (
O_ID varchar(20),
M_ID varchar(55), 
O_Date date, 
Amount bigint default 0,
primary key (O_ID),
constraint foreign key(M_ID) references Manufacturers(M_ID) on delete cascade
);
create table Products (
P_ID varchar(20),
P_Name varchar(55), 
M_ID varchar(20), 
Stock bigint,
primary key (P_ID),
constraint foreign key(M_ID) references Manufacturers(M_ID) on delete cascade
);
create table Clients (
C_ID varchar(20),
C_Name varchar(55), 
Contact varchar(12), 
Address varchar(255),
primary key (C_ID)
);

create table Deliveries (
D_ID varchar(20),
C_ID varchar(20), 
Employee_Assigned int, 
D_Status varchar(20),
D_date date,
Amount bigint default 0,
P_Status varchar(20),
primary key (D_ID),
constraint foreign key(C_ID) references Clients(C_ID) on delete cascade,
constraint foreign key(Employee_Assigned) references Employees(E_ID) on delete cascade
);

create view billidall as 
select O_ID as billid from Orders
union
select D_ID as billid from Deliveries;


DELIMITER //

CREATE FUNCTION checkid(id VARCHAR(20)) RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE result INT;

    -- Check if the parameter exists in the view
    SELECT COUNT(*) INTO result
    FROM billidall
    WHERE billid = id; -- Replace 'column_name_to_match' with the actual column name you want to match

    IF result > 0 THEN
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;
END //

DELIMITER ;



create table Billing_Details (
Bill_No varchar(20),
P_ID varchar(20), 
Quantity bigint,
Rate bigint,
primary key (Bill_No, P_ID),
constraint foreign key(P_ID) references Products(P_ID) on delete cascade
);

DELIMITER //

CREATE TRIGGER billingtrigger
BEFORE INSERT ON Billing_Details
FOR EACH ROW
BEGIN
    IF checkid(NEW.Bill_No) = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid data. Check constraint failed.';
    END IF;
END;
//

DELIMITER //

CREATE TRIGGER cascadedeltrigger
AFTER DELETE ON Deliveries
FOR EACH ROW
BEGIN
    DELETE FROM Billing_Details
    WHERE Billing_Details.Bill_No = OLD.D_ID;
END;
//

DELIMITER ;

DELIMITER //

CREATE TRIGGER cascadeordtrigger
AFTER DELETE ON Orders
FOR EACH ROW
BEGIN
    DELETE FROM Billing_Details
    WHERE Billing_Details.Bill_No = OLD.O_ID;
END;
//

DELIMITER ;


   SET SQL_SAFE_UPDATES = 0;

 INSERT INTO Employees (E_ID, E_Name, Designation, Contact, Email, Password, Address, Salary)
VALUES
  (101, 'Rajesh Kumar', 'Sales Manager', '7845123698', 'rajesh@gmail.com', 'raju123', '123 Main St, Mumbai, India', 95000),
  (102, 'Priya Singh', 'Human Resources Manager', '8034987651', 'priya@gmail.com', 'secure456', '456 Elm St, Delhi, India', 97000),
  (103, 'Amit Sharma', 'Chartered Accountant', '8498261370', 'amit@gmail.com', 'mysecret89', '789 Oak St, Bangalore, India', 92000),
  (104, 'Neha Gupta', 'Sales Representative', '8768439150', 'neha@gmail.com', 'emp987', '101 Pine St, Kolkata, India', 91000),
  (105, 'Sandeep Verma', 'Marketing Manager', '3819546072', 'sandeep@gmail.com', 'p@ssw0rd', '202 Birch St, Chennai, India', 95000),
  (106, 'Kavita Mehta', 'Sales Representative', '9652174830', 'kavita@gmail.com', 'key1234', '303 Maple St, Hyderabad, India', 97000),
  (107, 'Rahul Kapoor', 'Sales Representative', '9306498275', 'rahul@gmail.com', 'accgtd', '404 Cedar St, Pune, India', 94000),
  (108, 'Divya Sharma', 'Delivery Driver', '9427850139', 'divya@gmail.com', 'secLogin', '606 Oak St, Jaipur, India', 95000),
  (109, 'Pooja Singh', 'IT Support Specialist', '9108273465', 'pooja@gmail.com', 'pass321', '707 Elm St, Lucknow, India', 97000),
  (110, 'Arun Yadav', 'Delivery Driver', '9540768913', 'arun@gmail.com', '12secure', '808 Pine St, Chandigarh, India', 92000),
  (111, 'Anita Raj', 'Delivery Driver', '7531492680', 'anita@gmail.com', 'empPass', '909 Birch St, Bhopal, India', 95000);


INSERT INTO Manufacturers (M_ID, M_Name, Contact, Address, POC)
VALUES
    ('M101', 'Hindustan Unilever', '919876523147', 'Mumbai, Maharashtra', 101),
    ('M102', 'ITC', '919876643121', 'Kolkata, West Bengal', 102),
    ('M103', 'Nestle', '919876785921', 'Gurgaon, Haryana', 104),
    ('M104', 'Britannia Industries', '919876843253', 'Bengaluru, Karnataka', 104),
    ('M105', 'Tata Consumer Products', '919876949517', 'Mumbai, Maharashtra', 105),
    ('M106', 'Parle Agro', '919877089514', 'Mumbai, Maharashtra', 106),
    ('M107', 'Mother Dairy', '919877147657', 'Delhi, Delhi', 107),
    ('M108', 'Vadilal Industries', '919877223415', 'Ahmedabad, Gujarat', 108),
    ('M109', 'Haldiram’s', '919877359819', 'Nagpur, Maharashtra', 109),
    ('M110', 'Marico Limited', '919877461987', 'Mumbai, Maharashtra', 110);

    
    
    
    -- Populate the Products table with sample data
INSERT INTO Products (P_ID, P_Name, M_ID, Stock)
VALUES
    
    ('P202', 'Annapurna Salt', 'M101', 200),
    ('P203', 'Boost', 'M101', 150),
    ('P204', 'Bru Coffee', 'M101', 120),
    ('P205', 'Brooke Bond', 'M101', 180),
    ('P206', 'Knorr soups', 'M101', 250),
    
    ('P301', 'Sunfeast Mom’s Magic Butter Fills', 'M102', 100),
    ('P302', 'Yippee', 'M102', 150),
    ('P303', 'Aashirvaad Organic Atta', 'M102', 200),
    ('P304', 'Bingo!Cheese Nachos', 'M102', 120),
    ('P305', 'Tedhe Medhe Aloo Bhujia', 'M102', 180),
    
    ('P401', 'Nescafe', 'M103', 200),
    ('P402', 'Maggi', 'M103', 250),
    ('P403', 'Milkybar', 'M103', 150),
    ('P404', 'Milo', 'M103', 180),
    ('P405', 'Kit Kat', 'M103', 100),
    
    ('P501', 'MarieGold', 'M104', 120),
    ('P502', 'Tiger', 'M104', 180),
    ('P503', 'Nutrichoice', 'M104', 200),
    ('P504', 'Good Day', 'M104', 250),
    ('P505', '50 50', 'M104', 100),
    
    ('P601', 'Tata Salt', 'M105', 200),
    ('P602', 'Tata Tea', 'M105', 180),
    ('P603', 'Tata Sampann', 'M105', 250),
    ('P604', 'Tetley', 'M105', 150),
    ('P605', 'Himalayan mineral water', 'M105', 120),
    
    ('P701', 'Frooti', 'M106', 100),
    ('P702', 'Appy', 'M106', 150),
    ('P703', 'Appy Fizz', 'M106', 200),
    ('P704', 'BFizz', 'M106', 120),
    ('P705', 'SMOODH', 'M106', 180),
    
    ('P801', 'Mother dairy icecreams', 'M107', 250),
    ('P802', 'Mother dairy Paneer', 'M107', 200),
    ('P803', 'Mother dairy Ghee', 'M107', 180),
    
    ('P901', 'Vadilal Quick treat Veggie Nuggets', 'M108', 150),
    ('P902', 'Vadilal Quick treat Kala Chana', 'M108', 120),
    ('P903', 'Vadilal Rasgulla', 'M108', 200),
    ('P904', 'Vadilal Butterscotch Badam Milk Drink', 'M108', 180),
    
    ('P1001', 'Haldiram’s Soan Cake', 'M109', 250),
    ('P1002', 'Haldiram’s Fruit Cookies', 'M109', 200),
    ('P1003', 'Haldiram’s Cashew Jar', 'M109', 180),
    ('P1004', 'Haldiram’s Sweet Wonders', 'M109', 120),
    ('P1005', 'Haldiram’s Bhujia', 'M109', 100),
    
    ('P1101', 'Parachute Coconut Oil', 'M110', 180),
    ('P1102', 'Saffola Active', 'M110', 200),
    ('P1103', 'Livon Hair Serum', 'M110', 120),
    ('P1104', 'Set Wet Hair Gel', 'M110', 150),
    ('P1105', 'Parachute Advanced Body Lotion', 'M110', 250);

 
  
  


  
  INSERT INTO clients (C_ID, C_Name, Contact, Address)
VALUES
  ('1', 'Fresh Taste Distributors', '8263491705', '123 Main St, Mumbai, India'),
  ('2', 'Delicious Harvest Traders', '7521984630', '456 Elm St, Delhi, India'),
  ('3', 'Flavorful Eats Suppliers', '8942567103', '789 Oak St, Bangalore, India'),
  ('4', 'Savorful Food Wholesalers', '9718035246', '101 Pine St, Kolkata, India'),
  ('5', 'Tasty Bites Merchants', '8192675304', '202 Birch St, Chennai, India'),
  ('6', 'Yummy Munch Providers', '7950326481', '303 Maple St, Hyderabad, India'),
  ('7', 'Palate Pleaser Imports', '8234160957', '404 Cedar St, Pune, India'),
  ('8', 'Culinary Crafters', '9078643125', '505 Willow St, Ahmedabad, India'),
  ('9', 'Taste of Rajasthan Distributors', '7980521463', '606 Oak St, Jaipur, India'),
  ('10', 'Lucknow Delights & Co.', '8349067521', '707 Elm St, Lucknow, India'),
  ('11', 'Chandigarh Flavor Enterprises', '9687132405', '808 Pine St, Chandigarh, India'),
  ('12', 'Bhopal Food Artisans', '7830651492', '909 Birch St, Bhopal, India'),
  ('13', 'Pune Culinary Creations', '9254673180', '111 Elm St, Pune, India'),
  ('14', 'Jaipur Food Fantasies', '8503724619', '222 Oak St, Jaipur, India'),
  ('15', 'Chennai Gourmet Creations', '8719425036', '333 Maple St, Chennai, India'),
  ('16', 'Mumbai Food Express', '8247630195', '444 Cedar St, Mumbai, India'),
  ('17', 'Delhi Culinary Concepts', '7986142053', '555 Birch St, Delhi, India'),
  ('18', 'Kolkata Kitchen Creations', '9357128406', '666 Pine St, Kolkata, India'),
  ('19', 'Ahmedabad Food Crafters', '8462301975', '777 Willow St, Ahmedabad, India'),
  ('20', 'Lucknow Culinary Imports', '9721843650', '888 Oak St, Lucknow, India');
  

  
-- INSERT INTO Orders (O_ID, M_ID, O_Date, Amount)
-- VALUES
--      ('O1', 'M101', '2023-10-25', 50000),
--      ('O2', 'M102', '2023-10-26', 45000),
--     ('O3', 'M103', '2023-10-27', 60000);
-- INSERT INTO Deliveries (D_ID, C_ID, Employee_Assigned, D_Status, D_date, Amount, P_Status)
-- VALUES
--     ('D1', '1', 108, 'Delivered', '2023-10-26', 50000, 'Paid'),
--     ('D2', '2', 104, 'Delivered', '2023-10-27', 45000, 'Paid'),
--     ('D3', '3', 105, 'In Progress', '2023-10-28', 60000, 'Pending');
--     
-- insert into Billing_Details values("D1","P202", 50, 12);
-- insert into Billing_Details values("D1","P204", 500, 10);
-- insert into Billing_Details values("D1","P301", 40, 5);
-- insert into Billing_Details values("D2","P301", 35, 15);

-- insert into Billing_Details values("O1","P901", 100, 25);
-- insert into Billing_Details values("O1","P604", 75, 16);
-- insert into Billing_Details values("O1","P405", 120, 10);
-- insert into Billing_Details values("O2","P602", 50, 10);
-- insert into Billing_Details values("O2","P401", 140, 20);
