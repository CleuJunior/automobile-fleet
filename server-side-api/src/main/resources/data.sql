-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela COSTUMER_ENTITY                       --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', '2022-12-21 21:13:12', '2023-02-18 09:31:00');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', '2022-08-13 18:08:30', '2023-03-18 13:11:09');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Heloisa Aurora Jéssica dos Santos', '1988-08-12', 'heloisaauroradossantos@sha.com.br', '18895846006', 'Rua Astrogildo de Almeida, 328', '(47) 98101-9804', '2023-01-21 10:18:20', '2023-03-24 21:44:11');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Julio Marcelo Nelson Melo', '1974-03-05', 'julio.marcelo.melo@attglobal.net', '90897130198', 'Praça Vereador Osvaldo Mendonça, 752', '(79) 99539-6592', '2022-02-21 23:08:23', '2022-12-11 08:31:33');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Hugo Benjamin Barbosa', '1980-11-02', 'hugo.benjamin.barbosa@cathedranet.com.br', '74091777742', 'Travessa Jasmim, 101', '(68) 99602-7578', '2022-12-03 09:23:14', '2023-02-18 14:31:57');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Marta Ferreira Castro', '1995-06-22', 'martafc@gmail.com', '00230558184', 'Rua das Flores, 45', '(11) 98765-4321', '2022-09-18 11:22:33', '2023-03-21 16:55:44');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Pedro Henrique Silva', '1989-03-15', 'pedro.henrique.silva@outlook.com', '03482045429', 'Avenida Brasil, 2345', '(21) 99876-5432', '2022-10-20 15:42:00', '2023-03-22 09:11:22');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Luana Oliveira Costa', '1991-12-05', 'luanaoliveiracosta@gmail.com', '03780614280', 'Rua São Paulo, 123', '(31) 98765-4321', '2022-11-11 08:15:45', '2023-03-23 14:33:00');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Fernando Henrique Ribeiro', '1985-05-11', 'fernando.hr@gmail.com', '01234567890', 'Rua das Palmeiras, 67', '(41) 99876-5432', '2022-12-01 12:30:15', '2023-03-24 11:22:33');

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES ('Marcela Souza Santos', '1992-09-29', 'marcela.ss@gmail.com', '12345678901', 'Avenida Paulista, 1000', '(11) 98765-4321', '2023-01-02 18:05:00', '2023-03-24 16:44:11');

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

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Toyota', '1937-08-28 10:20:05');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Ford', '1903-06-16 13:24:07');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Volkswagen', '1937-05-28 15:50:39');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Ferrari', '1947-12-12 11:14:25');

INSERT INTO brand_entity (brand_name, created_at)
VALUES ('Honda', '1948-09-24 08:05:13');


-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela CATEGORY_ENTITY                       --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO category_entity (name, description, created_at)
VALUES ('SUVs', 'Veículos utilitários esportivos', '2022-02-18 09:31:02');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Carros Elétricos', 'Veículos movidos a energia elétrica', '2021-01-13 23:08:23');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Hatch', 'Categoria de carros com carroceria hatchback', '2020-11-8 21:44:11');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Picape', 'Categoria de carros com carroceria pickup', '2021-08-02 14:12:28');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Esportivo', 'Categoria de carros esportivos com alta performance', '2019-09-29 21:23:52');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Caminhões', 'Veículos de grande porte para transporte de cargas', '2018-06-15 10:27:44');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Carros de Luxo', 'Carros de alta qualidade e com grande valor agregado', '2022-01-05 15:20:17');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Minivans', 'Veículos familiares com grande espaço interno', '2020-07-03 08:15:59');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Coupé', 'Categoria de carros com carroceria coupé', '2019-04-22 17:54:01');

INSERT INTO category_entity (name, description, created_at)
VALUES ('Carros Antigos', 'Veículos com mais de 30 anos de fabricação', '2022-02-28 12:35:46');

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

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Capacidade do tanque', 'Especificação técnica que define a capacidade do tanque de combustível do veículo.', '2023-03-23 15:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Peso', 'Especificação técnica que define o peso do veículo.', '2023-03-23 16:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Comprimento', 'Especificação técnica que define o comprimento do veículo.', '2023-03-23 17:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Altura', 'Especificação técnica que define a altura do veículo.', '2023-03-23 18:00:00');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES ('Largura', 'Especificação técnica que define a largura do veículo.', '2023-03-23 19:00:00');


-- -------------------------------------------------------------------------------------
--                                                                                    --
--                  Inserção de dados na tabela CAR_ENTITY                            --
--                                                                                    --
-- -------------------------------------------------------------------------------------

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Série 3', 'Sedan médio da BMW', 98.77, true, 'XPT-1146', 1, 1, 'Preto' , '2023-03-23 14:30:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 2, 2, 'Amarelo' , '2023-03-23 12:00:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', 3, 3, 'Verde', '2020-11-8 21:44:11');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Kwid', 'Carro compacto da Renault',  88.99, true, 'KLC-0056', 4, 4, 'Azul', '2023-02-18 14:31:57');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('March', 'Carro compacto da Nissan', 148.99, false, 'OPL-456', 5, 5, 'Abobora', '2023-03-23 11:33:10');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Corolla', 'Sedan médio da Toyota', 120.50, true, 'DEF-1234', 6, 6, 'Branco', '2023-03-23 15:20:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Mustang', 'Esportivo da Ford', 200.00, false, 'GHJ-5678', 7, 7, 'Vermelho', '2023-03-23 16:15:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Gol', 'Carro compacto da Volkswagen', 80.00, true, 'IJK-9012', 8, 8, 'Prata', '2023-03-23 17:10:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('488', 'Esportivo da Ferrari', 1500.00, false, 'LMN-3456', 9, 9, 'Vermelho', '2023-03-23 18:05:00');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES ('Civic', 'Sedan médio da Honda', 110.00, true, 'OPQ-7890', 10, 10, 'Prata', '2023-03-23 19:00:00');

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

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (6, 6, '2023-03-20', '2023-03-25', 446.97, '2021-01-23 14:30:16', '2022-04-23 08:11:50');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (7, 7, '2023-03-21', '2023-03-28', 1000.00, '2023-03-18 21:18:34', '2023-03-14 21:15:31');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (8, 8, '2023-03-22', '2023-03-27', 400.00, '2020-05-09 11:22:44', '2023-03-23 14:40:03');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (9, 9, '2023-03-23', '2023-03-26', 4500.00, '2018-01-27 16:55:58', '2022-06-28 22:54:23');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES (10, 10, '2023-03-24', '2023-03-29', 550.00, '2022-11-09 22:41:48', '2023-03-23 14:40:53');

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

INSERT INTO car_specification (car_id, specification_id)
VALUES (6, 6);

INSERT INTO car_specification (car_id, specification_id)
VALUES (7, 7);

INSERT INTO car_specification (car_id, specification_id)
VALUES (8, 8);

INSERT INTO car_specification (car_id, specification_id)
VALUES (9, 9);

INSERT INTO car_specification (car_id, specification_id)
VALUES (10, 10);

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

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (6, CAST(NULL AS BLOB), '2023-02-14 21:15:31');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (7, CAST(NULL AS BLOB), '2023-01-23 22:54:23');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (8, CAST(NULL AS BLOB), '2023-03-08 11:33:10');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (9, CAST(NULL AS BLOB), '2023-01-14 13:11:09');

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES (10, CAST(NULL AS BLOB), '2023-01-21 21:44:11');
