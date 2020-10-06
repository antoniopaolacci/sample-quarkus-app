-- on kubernetes switch to use postgres, we need to do some adjustment against mysql

-- table already created by quarkus.hibernate-orm.database.generation = drop-and-create

/* 
CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age BIGINT NOT NULL,
  position VARCHAR(255) NOT NULL,
  organizationId BIGINT NOT NULL,
  departmentId BIGINT NOT NULL
);
*/

-- insert into

INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('John Smith', 30, 'Kubernetes Developer',1,1);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('Paul Walker', 40, 'Kubernetes Architect',1,2);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('John Snow', 35, 'Kubernetes Consultant',1,2);
INSERT INTO employee (name,age,position,organizationId,departmentId) VALUES  ('Elon Free', 35, 'Kubernetes BU Manager',1,2);
COMMIT;