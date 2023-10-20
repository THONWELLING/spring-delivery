CREATE TABLE product (
   id            UUID NOT NULL,
   name          VARCHAR(30) NOT NULL,
   description   VARCHAR(255) NOT NULL,
   price         DECIMAL NOT NULL,
   active        BOOLEAN NOT NULL,
   restaurant_id UUID NOT NULL,

   CONSTRAINT product_pk PRIMARY KEY ("id"),
   CONSTRAINT fk_product_on_restaurant FOREIGN KEY ("restaurant_id") REFERENCES restaurant ("id")
);