CREATE TABLE restaurant (
   id                UUID NOT NULL,
   name              VARCHAR(30) NOT NULL,
   delivery_rate     DOUBLE PRECISION NOT NULL,
   kitchen_id        UUID NOT NULL,
   registration_date timestamp not null,
   update_date       timestamp,
   zip_code          VARCHAR(8),
   street            VARCHAR(60),
   number            VARCHAR(6),
   complement        VARCHAR(50),
   neighborhood      VARCHAR(25),
   address_city_id   UUID,

   CONSTRAINT restaurant_pk PRIMARY KEY ("id"),
   CONSTRAINT fk_restaurant_on_kitchen      FOREIGN KEY ("kitchen_id") REFERENCES kitchen ("id")
);

CREATE TABLE restaurant_payment_mode (
  payment_mode_id UUID NOT NULL,
  restaurant_id   UUID NOT NULL
);
