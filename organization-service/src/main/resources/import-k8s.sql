-- table table already created by quarkus.hibernate-orm.database.generation = drop-and-create

/*
CREATE TABLE organization (
  id BIGINT SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);
*/

-- insert into

INSERT INTO organization (id,name,address) VALUES  (1,'GT 2000','Sylicon Valley 12');
INSERT INTO organization (id,name,address) VALUES  (2,'Solar Boost','California 59');
COMMIT;
