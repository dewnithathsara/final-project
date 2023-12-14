create database enchanted;

use enchanted;

CREATE TABLE users (
    uId VARCHAR(5) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    username VARCHAR(10) UNIQUE,
    password VARCHAR(10) UNIQUE);

INSERT INTO users VALUES('E001','Nemasha','nemasha@gmail.com','Manager','456');


CREATE TABLE customer (
    cId VARCHAR(5) PRIMARY KEY,
    cust_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    address VARCHAR(100),
    contact VARCHAR(10)
);

create table appointment(
aid varchar(5) primary key,
fee decimal(8), status varchar(100) ,
app_date Date,
app_time Time,
cid VARCHAR(5),
CONSTRAINT FOREIGN KEY (cid) REFERENCES customer (cId) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE event (
    eid VARCHAR(5) PRIMARY KEY,
    type VARCHAR(100),
    location VARCHAR(100),
    aId VARCHAR(5) NOT NULL,
    CONSTRAINT  FOREIGN KEY (aId) REFERENCES appointment (aid) ON DELETE CASCADE ON UPDATE CASCADE);


CREATE TABLE consulting_fee (
    conId VARCHAR(5) PRIMARY KEY,
    fee DECIMAL(6, 2), -- Using DECIMAL for monetary values with 2 decimal places
    description VARCHAR(100),
    cid VARCHAR(5) NOT NULL,
    CONSTRAINT  FOREIGN KEY (cid) REFERENCES customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);

create table feedback(
fid varchar(5),
cust_name varchar(100),
description varchar(100) );

create table employee(
empId varchar(5) primary key,
 name varchar(100) not null,
 email varchar(100) unique,
 contact VARCHAR(10),
  type varchar(100) not null);

  INSERT INTO employee VALUES
  ('E001','Nemasha','nema@gmail.com','0729631230','Manager');

  INSERT INTO employee VALUES
  ('E002','Thamasha','thamasha@gmail.com','0729191432','Designer');

  INSERT INTO employee VALUES
  ('E003','Eshani','eshani@gmail.com','0711948805','Team Leader');
CREATE TABLE eventRole (
    empId VARCHAR(5) NOT NULL,
    aId VARCHAR(5) NOT NULL,
    CONSTRAINT  FOREIGN KEY (empId) REFERENCES employee (empId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (aId) REFERENCES appointment (aid) ON DELETE CASCADE ON UPDATE CASCADE,
    task VARCHAR(100),
    task_status VARCHAR(100)
);

create  table service(
sid varchar(5) primary key,
package_name varchar(100) not null,
description varchar(100),
price decimal(8));

CREATE TABLE vendor_collobarating(
    eid VARCHAR(5) NOT NULL,
    Sid VARCHAR(5) NOT NULL,
    Vid VARCHAR(5) NOT NULL,
    feedback VARCHAR(200),
    time TIME,
    date DATE,
    price DECIMAL(10,2),
    PRIMARY KEY (eid, Sid, Vid),
    FOREIGN KEY (eid) REFERENCES event(eid),
    FOREIGN KEY (Sid) REFERENCES service(Sid),
    FOREIGN KEY (Vid) REFERENCES vendors(id)
);
