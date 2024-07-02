CREATE TABLE purchase_order (
    order_id     uuid PRIMARY KEY,
    subtotal     float(20),
    tax_delivery float(20),
    total_value  float(20),

    restaurant_id     UUID         NOT NULL,
    user_client_id    UUID         NOT NULL,
    payment_mode_id   UUID         NOT NULL,
    zip_code          VARCHAR(8)   NOT NULL,
    address_city_id   UUID,
    neighborhood      VARCHAR(30),
    street            VARCHAR(60)  NOT NULL,
    number            VARCHAR(6)   NOT NULL,
    complement        VARCHAR(50),
    status            VARCHAR(20)  NOT NULL,
    date_creation     TIMESTAMP    NOT NULL,
    date_confirmation TIMESTAMP,
    canceled_at       TIMESTAMP,
    date_delivered    TIMESTAMP,


    CONSTRAINT  fk_order_address_city FOREIGN KEY (address_city_id) REFERENCES city        (id),
    CONSTRAINT  fk_order_restaurant   FOREIGN KEY (restaurant_id)   REFERENCES restaurant  (id),
    CONSTRAINT  fk_order_user_client  FOREIGN KEY (user_client_id)  REFERENCES users_table (id),
    CONSTRAINT  fk_order_payment_mode FOREIGN KEY (payment_mode_id) REFERENCES payment_mode(id)

);