-- -------------------------------------------------------------------------------------
--                  DATA ENTRY FOR UNIT TESTS WITH DATABASE IN MEMORY                 --
-- -------------------------------------------------------------------------------------

INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
VALUES
('Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', '2022-12-21 21:13:12', '2023-02-18 09:31:00'),
('Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', '2022-08-13 18:08:30', '2023-03-18 13:11:09'),
('Marcela Souza Santos', '1992-09-29', 'marcela.ss@gmail.com', '12345678901', 'Avenida Paulista, 1000', '(11) 98765-4321', '2023-01-02 18:05:00', '2023-03-24 16:44:11');

INSERT INTO brand_entity (brand_name, created_at)
VALUES
('BMW', '2018-11-19 18:12:08'),
('Chevrolet', '2018-08-11 08:03:01'),
('Yamaha', '2021-07-22 22:09:11');

INSERT INTO category_entity (name, description, created_at)
VALUES
('SUVs', 'Veículos utilitários esportivos', '2022-12-24 12:45:55'),
('Carros Elétricos', 'Veículos movidos a energia elétrica', '2021-08-30 15:33:24'),
('Hatch', 'Categoria de carros com carroceria hatchback', '2020-07-02 14:56:58');

INSERT INTO specification_entity (specification_name, specification_description, created_at)
VALUES
('Motor', 'Especificação técnica que define o tipo e a potência do motor do veículo.', '2022-12-24 12:45:55'),
('Câmbio', 'Especificação técnica que define o tipo de transmissão do veículo.', '2021-08-30 15:33:24'),
('Direção', 'Especificação técnica que define o tipo de direção do veículo.', '2020-07-02 14:56:58');

INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
VALUES
('Série 3', 'Sedan médio da BMW', 98.77, true, 'XPT-1146', 1, 1, 'Preto', '2022-12-24 12:45:55'),
('Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 2, 2, 'Amarelo', '2021-08-30 15:33:24'),
('MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', 3, 3, 'Verde', '2020-07-02 14:56:58');

INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
VALUES
(1, 1, '2023-03-20', '2023-03-25', 493.85, '2022-12-29 12:45:55', '2023-01-12 09:33:28'),
(2, 2, '2023-03-21', '2023-03-28', 771.26, '2021-09-19 15:33:24', '2022-08-30 21:29:11'),
(3, 3, '2023-03-22', '2023-03-27', 346.65, '2020-08-12 08:31:59', '2021-01-03 12:53:13');

INSERT INTO car_specification (car_id, specification_id)
VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO car_image_entity (car_id, image, created_at)
VALUES
(1, BLOB(NULL), '2022-12-24 12:45:55'),
(2, BLOB(NULL), '2021-08-30 15:33:24'),
(3, BLOB(NULL), '2020-07-02 14:56:58');