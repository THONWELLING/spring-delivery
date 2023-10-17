CREATE TABLE product (
   id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   description VARCHAR(255) NOT NULL,
   price DECIMAL NOT NULL,
   active BOOLEAN NOT NULL,
   restaurant_id UUID NOT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);