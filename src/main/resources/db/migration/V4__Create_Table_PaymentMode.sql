CREATE TABLE payment_mode (
   id UUID NOT NULL,
   description VARCHAR(255) NOT NULL,
   CONSTRAINT pk_payment_mode PRIMARY KEY (id)
);