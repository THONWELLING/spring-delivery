CREATE TABLE payment_mode (
   id          UUID NOT NULL,
   description VARCHAR(255) NOT NULL,

   CONSTRAINT payment_mode_pk PRIMARY KEY ("id")
);