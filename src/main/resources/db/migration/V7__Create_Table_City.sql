CREATE TABLE city (
   id       UUID NOT NULL,
   name     VARCHAR(30) NOT NULL,
   state_id UUID NOT NULL,

   CONSTRAINT city_pk PRIMARY KEY ("id"),
   CONSTRAINT fk_city_on_state FOREIGN KEY ("state_id") REFERENCES state ("id")
);