CREATE TABLE "group" (
  id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   CONSTRAINT pk_group PRIMARY KEY (id)
);

CREATE TABLE group_permission (
  group_id UUID NOT NULL,
   permission_id UUID NOT NULL
);

ALTER TABLE group_permission ADD CONSTRAINT fk_groper_on_group FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE group_permission ADD CONSTRAINT fk_groper_on_permission FOREIGN KEY (permission_id) REFERENCES permission (id);