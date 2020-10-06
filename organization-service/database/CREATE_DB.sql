-- Create the database
CREATE DATABASE organizationdb;

-- Create the user and grant access to localhost
CREATE USER 'organizationuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'organizationuser'@'localhost' IDENTIFIED BY 'password';

-- Make user admin of database
GRANT ALL PRIVILEGES ON organizationdb.* TO 'organizationuser';

-- Switch to database to create the schema
USE organizationdb;

-- table

CREATE TABLE organization (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);

-- insert into

INSERT INTO organization (name,address) VALUES  ('GT 2000','Sylicon Valley 12');
INSERT INTO organization (name,address) VALUES  ('Solar Boost','California 59');
COMMIT;


