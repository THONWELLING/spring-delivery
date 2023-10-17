CREATE TABLE "collection" (
   id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   CONSTRAINT pk_collection PRIMARY KEY (id)
);

CREATE TABLE collection_permission (
  collection_id UUID NOT NULL,
   permission_id UUID NOT NULL
);

ALTER TABLE collection_permission ADD CONSTRAINT fk_groper_on_collection FOREIGN KEY (collection_id) REFERENCES "collection" (id);

ALTER TABLE collection_permission ADD CONSTRAINT fk_groper_on_permission FOREIGN KEY (permission_id) REFERENCES permission (id);