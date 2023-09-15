INSERT INTO kitchen (id, name) VALUES
	 ('cc8af648-7ad4-4f15-9af7-b9434a90e404','Brasileira'),
	 ('6c7d3075-84f9-4794-b6bf-fca594407f83','Americana'),
	 ('8039e5b8-229c-4b08-8826-88139fffafee','Mexicana'),
	 ('793294de-5f80-421c-8ecc-d8722008bb54','Italiana'),
	 ('3a52c098-2599-4028-a87f-132a4ebf1acb','Argentina');


INSERT INTO state (id, name) VALUES
	 ('d6e2bad0-4a81-4a95-9b0d-637cf5f9d651','São Paulo'),
	 ('9488e024-5539-49e6-bbf3-658ea0e06cec','Minas Gerais'),
	 ('6f420257-1147-443f-ab5c-1443b7ebdfdc','Rio de Janeiro'),
	 ('7c27aae2-52a4-4cbb-a09d-471ea012f5e5','Paraíba'),
	 ('21828116-1ab5-4b79-9f40-fa1b53e68e88','New York');


INSERT INTO city (id, name, state_id) VALUES
	('e062f2d8-a4a4-4518-bd78-5a40c04872cf', 'Belo Horizonte', '9488e024-5539-49e6-bbf3-658ea0e06cec'),
	('64a87dd8-24cb-4e96-b7e9-1b98087ff5a6', 'Ouro Preto', '9488e024-5539-49e6-bbf3-658ea0e06cec'),
	('20e86882-251e-41d5-9c17-eaefd58c7ebb', 'Mariana', '9488e024-5539-49e6-bbf3-658ea0e06cec'),
	('a29746e4-5456-4478-b576-7598e6d751d5', 'Brumadinho', '9488e024-5539-49e6-bbf3-658ea0e06cec'),
	('198eebd1-2d24-4e8d-bf64-14bfaefc382c', 'São Paulo Capital', 'd6e2bad0-4a81-4a95-9b0d-637cf5f9d651'),
	('bbf6a6d6-a2b5-4bda-84ba-9f082433578c', 'Guarulhos', 'd6e2bad0-4a81-4a95-9b0d-637cf5f9d651'),
	('b42fdccf-65a1-4e36-af1c-0b48ab66c456','São Caetano', 'd6e2bad0-4a81-4a95-9b0d-637cf5f9d651'),
	('5f4ab5f8-5800-4835-8be7-8bb4ef3ccc47', 'Holambra', 'd6e2bad0-4a81-4a95-9b0d-637cf5f9d651'),
	('5c731c67-e10d-4e4a-bedd-74d02b359d76', 'Rio de Janeiro Capital', '6f420257-1147-443f-ab5c-1443b7ebdfdc'),
	('4e26c52e-651d-46eb-920e-19208e2c3ffc', 'Petrópolis', '6f420257-1147-443f-ab5c-1443b7ebdfdc');
INSERT INTO city (id, name, state_id) VALUES
	('d3262f5a-f6db-4fc2-aec3-b5a579635009', 'Paraty', '6f420257-1147-443f-ab5c-1443b7ebdfdc'),
	('79ad2945-b3dc-4da2-8463-30a8cc7b15d6', 'Angra dos Reis', '6f420257-1147-443f-ab5c-1443b7ebdfdc'),
	('88df943a-e521-47e3-8cb3-7ce49fed3d4a', 'João Pessoa', '7c27aae2-52a4-4cbb-a09d-471ea012f5e5'),
	('f3a3ba37-8bc7-43d2-941e-6db88d53f056', 'Campina Grande', '7c27aae2-52a4-4cbb-a09d-471ea012f5e5'),
	('d3462789-c7ee-4a7f-930d-1874af4235fe', 'Cabaceiras', '7c27aae2-52a4-4cbb-a09d-471ea012f5e5'),
	('5d15d4d5-68b6-4534-a123-1a1b5da8c390', 'Sousa', '7c27aae2-52a4-4cbb-a09d-471ea012f5e5'),
	('0a068a2b-fe25-4e98-a71b-9f1d8c9b4f87', 'New York', '21828116-1ab5-4b79-9f40-fa1b53e68e88');


INSERT INTO restaurant (id, delivery_rate, name, kitchen_id, address_complement, address_neighborhood, address_number, address_street, address_zip-code , address_city-id) VALUES
	 ('f5a5150a-f67a-4743-9c69-62f0214d3302',9.0,'Mac Donalds','6c7d3075-84f9-4794-b6bf-fca594407f83',NULL,NULL,NULL,NULL,NULL,NULL),
	 ('32f4c082-277d-4c1c-a166-62e6eae310c9',9.0,'Comida Mineira','cc8af648-7ad4-4f15-9af7-b9434a90e404',NULL,NULL,NULL,NULL,NULL,NULL),
	 ('65db8197-8852-434c-8a8b-a557ee340fb0',12.0,'Macaxeira','cc8af648-7ad4-4f15-9af7-b9434a90e404',NULL,NULL,NULL,NULL,NULL,NULL),
	 ('8e6070fd-899f-41eb-bb8f-da2fcdf53d6a',7.0,'Burger King Patch','6c7d3075-84f9-4794-b6bf-fca594407f83',NULL,NULL,NULL,NULL,NULL,NULL),
	 ('e16e9b9d-e85e-4e72-bee6-552eab04aa34',15.0,'Eleven Madison Park','6c7d3075-84f9-4794-b6bf-fca594407f83','In Front Of Madson Square Park','Greenwich Village','11','Madson Avenue','10010','0a068a2b-fe25-4e98-a71b-9f1d8c9b4f87');


INSERT INTO payment_mode (id, description) VALUES
	 ('a74aae80-7ec3-4d01-9156-78360d4336d9','Cartão de Crédito'),
	 ('dc46043c-218c-4e8f-a63a-74ebadbde0bd','Cartão de Débto'),
	 ('05173319-a842-4841-8f51-14aab137ea4b','PIX'),
	 ('5e7b1415-4667-44eb-b909-d83a35a4174f','Dinheiro');


INSERT INTO restaurants_payment_mode(restaurant_id, payment_mode_id) VALUES
    ("f5a5150a-f67a-4743-9c69-62f0214d3302", "a74aae80-7ec3-4d01-9156-78360d4336d9");
    ("f5a5150a-f67a-4743-9c69-62f0214d3302", "dc46043c-218c-4e8f-a63a-74ebadbde0bd");
    ("f5a5150a-f67a-4743-9c69-62f0214d3302", "05173319-a842-4841-8f51-14aab137ea4b");
    ("f5a5150a-f67a-4743-9c69-62f0214d3302", "5e7b1415-4667-44eb-b909-d83a35a4174f");
    ("65db8197-8852-434c-8a8b-a557ee340fb0", "a74aae80-7ec3-4d01-9156-78360d4336d9");
    ("65db8197-8852-434c-8a8b-a557ee340fb0", "dc46043c-218c-4e8f-a63a-74ebadbde0bd");
    ("65db8197-8852-434c-8a8b-a557ee340fb0", "05173319-a842-4841-8f51-14aab137ea4b");
    ("65db8197-8852-434c-8a8b-a557ee340fb0", "5e7b1415-4667-44eb-b909-d83a35a4174f");


INSERT INTO permission (id, description, name) VALUES
	 ('8b353595-b099-42f6-824a-3980f64eb13f','Permite Consultar Cozinhas','CONSULTAR_COZINHAS'),
	 ('f5648bba-bb14-46dc-863b-9b4f2c6655f1','Permite Editar Cozinhas','EDITAR_COZINHAS');
