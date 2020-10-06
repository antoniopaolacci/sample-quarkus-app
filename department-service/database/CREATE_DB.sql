-- Create the database
CREATE DATABASE departmentdb;

-- Create the user and grant access to localhost
CREATE USER 'departmentuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'departmentuser'@'localhost' IDENTIFIED BY 'password';

-- Make user admin of database
GRANT ALL PRIVILEGES ON departmentdb.* TO 'departmentuser';

-- Switch to database to create the schema
USE departmentdb;

-- table

CREATE TABLE department (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  organizationId BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL
);

-- insert into

INSERT INTO department (organizationId,name) VALUES  (1,'IT Department');
INSERT INTO department (organizationId,name) VALUES  (2,'Finance Department');
COMMIT;


