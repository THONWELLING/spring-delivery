CREATE TABLE payment_mode (
  id UUID NOT NULL,
   description VARCHAR(100) NOT NULL,
   CONSTRAINT pk_payment_mode PRIMARY KEY (id)
);