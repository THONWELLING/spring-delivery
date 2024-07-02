CREATE TABLE users_table (
   id                UUID PRIMARY KEY,
   name              VARCHAR(30)  NOT NULL,
   email             VARCHAR(100) NOT NULL,
   password          VARCHAR(15)  NOT NULL,
   registration_date TIMESTAMP    NOT NULL
);

CREATE TABLE user_collection (
  collection_id UUID NOT NULL,
  user_id       UUID NOT NULL,

  CONSTRAINT fk_user_collection_on_collection FOREIGN KEY (collection_id) REFERENCES collection (id),
  CONSTRAINT fk_user_collection_on_user FOREIGN KEY (user_id) REFERENCES users_table (id)
);