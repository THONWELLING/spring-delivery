create table order (
    order_id uuid not null,
    subtotal float(53),
    tax_delivery float(53),
    total_value float(53),

    restaurant_id uuid not null,
    user_client_id uuid not null,
    payment_mode_id uuid not null,

    zip_code varchar(8) not null,
    address_city_id uuid,
    neighborhood varchar(25),
    street varchar(60) not null,
    number varchar(6) not null,
    complement varchar(50),

    status varchar(10) not null,
    date_creation timestamp(6) not null,
    date_confirmation timestamp(6),
    canceled_at timestamp(6),
    date_delivered timestamp(6),

    primary key (order_id)

    add constraint fk_order_address_city foreign key (address_city_id) references city,
    add constraint fk_order_restaurant foreign key (restaurant_id) references restaurant,
    add constraint fk_order_user_cllient foreign key (user_client_id) references user,
    add constraint fk_order_payment_mode foreign key (payment_mode_id) references payment_mode;

);