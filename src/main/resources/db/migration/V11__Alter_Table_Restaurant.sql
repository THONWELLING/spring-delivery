ALTER TABLE restaurant_payment_mode ADD CONSTRAINT uc_restaurant_payment_mode_payment_mode UNIQUE (payment_mode_id);

ALTER TABLE restaurant ADD CONSTRAINT FK_RESTAURANT_ON_ADDRESS_CITY FOREIGN KEY (address_city_id) REFERENCES city (id);

ALTER TABLE restaurant ADD CONSTRAINT FK_RESTAURANT_ON_KITCHEN FOREIGN KEY (kitchen_id) REFERENCES kitchen (id);

ALTER TABLE restaurant_payment_mode ADD CONSTRAINT fk_respaymod_on_payment_mode FOREIGN KEY (payment_mode_id) REFERENCES payment_mode (id);

ALTER TABLE restaurant_payment_mode ADD CONSTRAINT fk_respaymod_on_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);