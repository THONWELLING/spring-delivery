CREATE TABLE "user" (
  id UUID NOT NULL,
   name VARCHAR(30) NOT NULL,
   email VARCHAR(100) NOT NULL,
   password VARCHAR(15) NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_group (
  group_id UUID NOT NULL,
   user_id UUID NOT NULL
);

ALTER TABLE user_group ADD CONSTRAINT fk_usegro_on_group FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE user_group ADD CONSTRAINT fk_usegro_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);