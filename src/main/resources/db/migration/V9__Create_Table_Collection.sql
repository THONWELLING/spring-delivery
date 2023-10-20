CREATE TABLE collection (
   id   UUID NOT NULL,
   name VARCHAR(30) NOT NULL,

   CONSTRAINT collection_pk PRIMARY KEY ("id")
);

CREATE TABLE collection_permission (
  collection_id UUID NOT NULL,
  permission_id UUID NOT NULL,

  CONSTRAINT fk_groper_on_collection FOREIGN KEY ("collection_id") REFERENCES collection ("id"),
  CONSTRAINT fk_groper_on_permission FOREIGN KEY ("permission_id") REFERENCES permission ("id")
);