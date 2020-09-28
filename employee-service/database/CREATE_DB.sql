-- Create the database
CREATE DATABASE employeedb;

-- Create the user and grant access to localhost
CREATE USER 'employeeuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'employeeuser'@'localhost' IDENTIFIED BY 'password';

-- Make user admin of database
GRANT ALL PRIVILEGES ON employeedb.* TO 'employeeuser';

-- Switch to database to create the schema
USE employeedb;

-- table

CREATE TABLE employee (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age BIGINT NOT NULL,
  position VARCHAR(255) NOT NULL,
  organizationId BIGINT NOT NULL,
  departmentId BIGINT NOT NULL,
  date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- insert into

INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('John Smith',30,'Junior Developer',1,1);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('Paul Walker',40,'Senior Architect',1,2);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('John Snow',35,'Senior Consultant',1,2);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('Patrik Serje',35,'Unit Manager',1,2);
COMMIT;
