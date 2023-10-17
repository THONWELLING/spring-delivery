create table order_item (
    item_id uuid not null,
    quantity integer not null,
    unit_price float(53),
    total_price float(53),
    observation varchar(100),
    order_id uuid not null,
    product_id uuid not null,

    primary key (item_id)

    add constraint fk_item_order_order  foreign key (order_id)  references order,
     add constraint fk_item_order_product foreign key (product_id) references product;
);