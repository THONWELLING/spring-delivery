CREATE TABLE purchase_order (
    order_id uuid not null,
    subtotal float(53),
    tax_delivery float(53),
    total_value float(53),

    restaurant_id     UUID not null,
    user_client_id    UUID not null,
    payment_mode_id   UUID not null,

    zip_code          varchar(8) not null,
    address_city_id   UUID,
    neighborhood      varchar(25),
    street            varchar(60)  not null,
    number            varchar(6)   not null,
    complement        varchar(50),

    status            varchar(10)  not null,
    date_creation     timestamp(6) not null,
    date_confirmation timestamp(6),
    canceled_at       timestamp(6),
    date_delivered    timestamp(6),

    CONSTRAINT  purchase_order_pk     PRIMARY KEY (order_id),
    CONSTRAINT  fk_order_address_city FOREIGN KEY ("address_city_id") REFERENCES city ("id"),
    CONSTRAINT  fk_order_restaurant   FOREIGN KEY ("restaurant_id")   REFERENCES restaurant ("id"),
    CONSTRAINT  fk_order_user_client  FOREIGN KEY ("user_client_id")  REFERENCES "user" ("id"),
    CONSTRAINT  fk_order_payment_mode FOREIGN KEY ("payment_mode_id") REFERENCES payment_mode("id")

);