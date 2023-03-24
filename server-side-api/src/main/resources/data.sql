-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela COSTUMER_ENTITY                       --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO costumer_entity (costumer_name, costumer_bd, costumer_email, drive_license, address, costumer_phone_number, created_at, update_at)
VALUES ('Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', '2022-12-21 21:13:12', '2023-02-18 09:31:00');

INSERT INTO costumer_entity (costumer_name, costumer_bd, costumer_email, drive_license, address, costumer_phone_number, created_at, update_at)
VALUES ('Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', '2022-08-13 18:08:30', '2023-03-18 13:11:09');

INSERT INTO costumer_entity (costumer_name, costumer_bd, costumer_email, drive_license, address, costumer_phone_number, created_at, update_at)
VALUES ('Heloisa Aurora Jéssica dos Santos', '1988-08-12', 'heloisaauroradossantos@sha.com.br', '18895846006', 'Rua Astrogildo de Almeida, 328', '(47) 98101-9804', '2023-01-21 10:18:20', '2023-03-24 21:44:11');

INSERT INTO costumer_entity (costumer_name, costumer_bd, costumer_email, drive_license, address, costumer_phone_number, created_at, update_at)
VALUES ('Julio Marcelo Nelson Melo', '1974-03-05', 'julio.marcelo.melo@attglobal.net', '90897130198', 'Praça Vereador Osvaldo Mendonça, 752', '(79) 99539-6592', '2022-02-21 23:08:23', '2022-12-11 08:31:33');

INSERT INTO costumer_entity (costumer_name, costumer_bd, costumer_email, drive_license, address, costumer_phone_number, created_at, update_at)
VALUES ('Hugo Benjamin Barbosa', '1980-11-02', 'hugo.benjamin.barbosa@cathedranet.com.br', '74091777742', 'Travessa Jasmim, 101', '(68) 99602-7578', '2022-12-03 09:23:14', '2023-02-18 14:31:57');

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela BRAND_ENTITY                          --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('BMW', '1916-03-07 21:44:11');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Chevrolet', '1911-11-03 13:00:00');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Yamaha', '1955-07-01 09:31:02');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Renault', '1898-12-24 14:40:53');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Nissan', '1933-12-26 08:31:33');

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela CATEGORY_ENTITY                       --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO category_entity (category_name, category_description, created_at)
VALUES ('SUVs', 'Veículos utilitários esportivos', '2022-02-18 09:31:02');

INSERT INTO category_entity (category_name, category_description, created_at)
VALUES ('Carros Elétricos', 'Veículos movidos a energia elétrica', '2021-01-13 23:08:23');

INSERT INTO category_entity (category_name, category_description, created_at)
VALUES ('Hatch', 'Categoria de carros com carroceria hatchback', '2020-11-8 21:44:11');

INSERT INTO category_entity (category_name, category_description, created_at)
VALUES ('Picape', 'Categoria de carros com carroceria pickup', '2021-08-02 14:12:28');

INSERT INTO category_entity (category_name, category_description, created_at)
VALUES ('Esportivo', 'Categoria de carros esportivos com alta performance', '2019-09-29 21:23:52');;

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                Inserção de dados na tabela SPECIFICATION_ENTITY                    --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Motor', 'Especificação técnica que define o tipo e a potência do motor do veículo.', '2023-03-23 10:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Câmbio', 'Especificação técnica que define o tipo de transmissão do veículo.', '2023-03-23 11:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Direção', 'Especificação técnica que define o tipo de direção do veículo.', '2023-03-23 12:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Freios', 'Especificação técnica que define o tipo de sistema de freios do veículo.', '2023-03-23 13:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Suspensão', 'Especificação técnica que define o tipo de suspensão do veículo.', '2023-03-23 14:00:00');

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela CAR_ENTITY                            --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Série 3', 'Sedan médio da BMW', 98.77, true, 'XPT-1146', 1, 5, 'Preto' , '2023-03-23 14:30:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 2, 3, 'Amarelo' , '2023-03-23 12:00:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', 4, 4, 'Verde', '2020-11-8 21:44:11');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Kwid', 'Carro compacto da Renault',  88.99, true, 'KLC-0056', 5, 1, 'Azul', '2023-02-18 14:31:57');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('March', 'Carro compacto da Nissan', 148.99, false, 'OPL-456', 3, 2, 'Abobora', '2023-03-23 11:33:10');

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela RENTAL_ENTITY                         --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (1, 1, '2023-03-20', '2023-03-25', 493.85, '2021-01-23 14:30:16', '2022-04-23 08:11:50');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (2, 2, '2023-03-21', '2023-03-28', 771.26, '2023-03-18 21:18:34', '2023-03-14 21:15:31');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (3, 3, '2023-03-22', '2023-03-27', 346.65, '2020-05-09 11:22:44', '2023-03-23 14:40:03');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (4, 4, '2023-03-23', '2023-03-26', 266.97, '2018-01-27 16:55:58', '2022-06-28 22:54:23');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (5, 5, '2023-03-24', '2023-03-29', 744.95, '2022-11-09 22:41:48', '2023-03-23 14:40:53');

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                 Inserção de dados na tabela CAR_SPECIFICATION                      --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO car_specification (car_id, specification_id)
VALUES (1, 1);

INSERT INTO car_specification (car_id, specification_id)
VALUES (2, 2);

INSERT INTO car_specification (car_id, specification_id)
VALUES (3, 3);

INSERT INTO car_specification (car_id, specification_id)
VALUES (4, 4);

INSERT INTO car_specification (car_id, specification_id)
VALUES (5, 5);

-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela CAR_IMAGE_ENTITY                      --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (1, CAST(NULL AS BLOB), '2023-02-14 21:15:31');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (2, CAST(NULL AS BLOB), '2023-01-23 22:54:23');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (3, CAST(NULL AS BLOB), '2023-03-08 11:33:10');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (4, CAST(NULL AS BLOB), '2023-01-14 13:11:09');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (5, CAST(NULL AS BLOB), '2023-01-21 21:44:11');
