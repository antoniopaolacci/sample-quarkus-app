-- table table already created by quarkus.hibernate-orm.database.generation = drop-and-create

/*
CREATE TABLE department (
  id BIGINT SERIAL PRIMARY KEY,
  organizationId BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
);
*/

-- insert into

INSERT INTO department (id,organizationId,name) VALUES  (1,1,'IT Department');
INSERT INTO department (id,organizationId,name) VALUES  (2,1,'Finance Department');
COMMIT;
