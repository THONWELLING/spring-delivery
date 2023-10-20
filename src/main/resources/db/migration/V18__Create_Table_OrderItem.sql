CREATE TABLE order_item (
    item_id UUID    not null,
    quantity        integer not null,
    unit_price      float(53),
    total_price     float(53),
    observation     varchar(100),
    order_id UUID   not null,
    product_id UUID not null,

    CONSTRAINT  order_ITEM_pk         PRIMARY KEY (item_id),
    CONSTRAINT  fk_item_order_order   FOREIGN KEY ("order_id")   REFERENCES purchase_order ("order_id"),
    CONSTRAINT  fk_item_order_product FOREIGN KEY ("product_id") REFERENCES product ("id")

);