CREATE TABLE "user" (
   id                UUID NOT NULL,
   name              VARCHAR(30) NOT NULL,
   email             VARCHAR(100) NOT NULL,
   password          VARCHAR(15) NOT NULL,
   registration_date timestamp not null,

   CONSTRAINT user_pk PRIMARY KEY ("id")
);

CREATE TABLE user_collection (
  collection_id UUID NOT NULL,
  user_id       UUID  NOT NULL,

  CONSTRAINT fk_usegro_on_collection FOREIGN KEY ("collection_id") REFERENCES collection ("id"),
  CONSTRAINT fk_usegro_on_user FOREIGN KEY ("user_id") REFERENCES "user" ("id")
);