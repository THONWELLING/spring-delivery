CREATE TABLE permission (
  id UUID NOT NULL,
   name VARCHAR(20) NOT NULL,
   description VARCHAR(100),
   CONSTRAINT pk_permission PRIMARY KEY (id)
);