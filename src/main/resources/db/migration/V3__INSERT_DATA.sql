-- -------------------------------------------------------------------------------------
--                              Inserir clientes                                      --
-- -------------------------------------------------------------------------------------

INSERT INTO costumer_entity VALUES
                                ('f9ed81db-f90a-42d4-b7e4-d554d8f338fd', 'Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', NOW(), NOW()),
                                ('ff68e22e-633f-4fe3-b482-590c7163b7e1', 'Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', NOW(), NOW()),
                                ('0723e7a1-ec12-4cdb-b4d5-6169dba540c6', 'Heloisa Aurora Jéssica dos Santos', '1988-08-12', 'heloisaauroradossantos@sha.com.br', '18895846006', 'Rua Astrogildo de Almeida, 328', '(47) 98101-9804', NOW(), NOW()),
                                ('9960a044-0f78-4489-8e98-211cb294eee3', 'Julio Marcelo Nelson Melo', '1974-03-05', 'julio.marcelo.melo@attglobal.net', '90897130198', 'Praça Vereador Osvaldo Mendonça, 752', '(79) 99539-6592', NOW(), NOW()),
                                ('98cf1d75-69c4-4e22-aec4-622c1251d7ed', 'Hugo Benjamin Barbosa', '1980-11-02', 'hugo.benjamin.barbosa@cathedranet.com.br', '74091777742', 'Travessa Jasmim, 101', '(68) 99602-7578', NOW(), NOW()),
                                ('b7bf613b-6619-4a72-abb6-5dc6734b9ae3', 'Marta Ferreira Castro', '1995-06-22', 'martafc@gmail.com', '00230558184', 'Rua das Flores, 45', '(11) 98765-4321', NOW(), NOW()),
                                ('5c6d5948-2c06-487c-bd3e-9ae23ded960b', 'Pedro Henrique Silva', '1989-03-15', 'pedro.henrique.silva@outlook.com', '03482045429', 'Avenida Brasil, 2345', '(21) 99876-5432', NOW(), NOW()),
                                ('3b77df60-67df-4071-ac29-abbd451da1c5', 'Luana Oliveira Costa', '1991-12-05', 'luanaoliveiracosta@gmail.com', '03780614280', 'Rua São Paulo, 123', '(31) 98765-4321', NOW(), NOW()),
                                ('e5a448e3-feaf-4535-9ac0-92b1471de468', 'Fernando Henrique Ribeiro', '1985-05-11', 'fernando.hr@gmail.com', '01234567890', 'Rua das Palmeiras, 67', '(41) 99876-5432', NOW(), NOW()),
                                ('a242decb-e589-4ecb-be7d-b7ff6f20ade5', 'Marcela Souza Santos', '1992-09-29', 'marcela.ss@gmail.com', '12345678901', 'Avenida Paulista, 1000', '(11) 98765-4321', NOW(), NOW());

-- -------------------------------------------------------------------------------------
--                              Inserir marca                                         --
-- -------------------------------------------------------------------------------------

INSERT INTO brand_entity VALUES
                             ('51208e86-1f63-4e18-ae59-941fd5e342cf', 'BMW', NOW()),
                             ('c2f73e68-d820-46e5-bc37-14ed6d2e8759', 'Chevrolet', NOW()),
                             ('2b7607ef-c314-4e75-992f-41fbd1a30a27', 'Yamaha', NOW()),
                             ('b306bf4e-ab6d-4c85-8838-dafcc78e1802', 'Renault', NOW()),
                             ('ab42bf2a-0739-4bc5-b901-4890af4a3ec9', 'Nissan', NOW()),
                             ('9483ddf3-3bc2-4ac4-abba-4aa5b59625fb', 'Toyota', NOW()),
                             ('6cf235ae-0759-4ac8-925a-476de5a4f5d0', 'Ford', NOW()),
                             ('651d807b-b34f-4818-856d-00bb3ff09d71', 'Volkswagen', NOW()),
                             ('4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe', 'Ferrari', NOW()),
                             ('3f831dbb-de3b-4b1a-95dc-602cdeaa7012', 'Honda', NOW());

-- -------------------------------------------------------------------------------------
--                              Inserir categoria                                     --
-- -------------------------------------------------------------------------------------

INSERT INTO category_entity VALUES
                                ('1deb1e69-6b1a-4d88-9a1b-a9889398b54a', 'SUVs', 'Veículos utilitários esportivos', NOW()),
                                ('89658392-1ce5-4523-8b08-b7dd9322d57a', 'Carros Elétricos', 'Veículos movidos a energia elétrica', NOW()),
                                ('1af3289c-f302-4a95-95f6-c9d05c0aa50b', 'Hatch', 'Categoria de carros com carroceria hatchback', NOW()),
                                ('2e46c0aa-011f-4fc2-81e8-5365de43fff7', 'Picape', 'Categoria de carros com carroceria pickup', NOW()),
                                ('9755db80-c218-4671-b147-22795510c74e', 'Esportivo', 'Categoria de carros esportivos com alta performance', NOW()),
                                ('345f40dd-f528-4f6a-83c4-2d47b304f557', 'Caminhões', 'Veículos de grande porte para transporte de cargas', NOW()),
                                ('62a6ddee-9308-4bbc-a819-5064597383a3', 'Carros de Luxo', 'Carros de alta qualidade e com grande valor agregado', NOW()),
                                ('3b0872df-d47a-48f4-964e-b6a099964ee7', 'Minivans', 'Veículos familiares com grande espaço interno', NOW()),
                                ('146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9', 'Coupé', 'Categoria de carros com carroceria coupé', NOW()),
                                ('dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d', 'Carros Antigos', 'Veículos com mais de 30 anos de fabricação', NOW());

-- -------------------------------------------------------------------------------------
--                              Inserir especificação                                 --
-- -------------------------------------------------------------------------------------

INSERT INTO specification_entity VALUES
                                     ('7eef4f65-96a1-4463-8a7f-3a5df541f310','Motor', 'Especificação técnica que define o tipo e a potência do motor do veículo.', NOW()),
                                     ('194b82cc-22a3-4058-a9fa-249ad5340268', 'Câmbio', 'Especificação técnica que define o tipo de transmissão do veículo.', NOW()),
                                     ('3a16cf2b-30d1-4f88-a366-1038746441d4', 'Direção', 'Especificação técnica que define o tipo de direção do veículo.', NOW()),
                                     ('de88412a-d516-47fd-a723-1ec5a13d8b44', 'Freios', 'Especificação técnica que define o tipo de sistema de freios do veículo.', NOW()),
                                     ('d20d196b-c329-4a32-a76b-cf5802affcfc', 'Suspensão', 'Especificação técnica que define o tipo de suspensão do veículo.', NOW()),
                                     ('bcae4e4e-0e7a-41a2-bc24-560077557c0b', 'Capacidade do tanque', 'Especificação técnica que define a capacidade do tanque de combustível do veículo.', NOW()),
                                     ('8f4647ee-2ef1-4500-8a28-a0d642665c81', 'Peso', 'Especificação técnica que define o peso do veículo.', NOW()),
                                     ('7c01a5f0-41b3-4a15-adc5-b0b380bb90e2', 'Comprimento', 'Especificação técnica que define o comprimento do veículo.', NOW()),
                                     ('ddc7f420-47b1-4f7e-bc35-07765cbd811d', 'Altura', 'Especificação técnica que define a altura do veículo.', NOW()),
                                     ('6b83e4cd-ead6-4af0-8e1e-4c332a842717', 'Largura', 'Especificação técnica que define a largura do veículo.', NOW());

-- -------------------------------------------------------------------------------------
--                              Inserir carro                                         --
-- -------------------------------------------------------------------------------------

INSERT INTO car_entity VALUES
                           ('55491147-1d2f-455e-9958-1e35f1df5a82', 'Série 3', 'Sedan médio da BMW', 98.77, true, 'XPT-1146', '51208e86-1f63-4e18-ae59-941fd5e342cf', '1deb1e69-6b1a-4d88-9a1b-a9889398b54a', 'Preto', NOW()),
                           ('01f90d99-85e3-42c0-b55d-1fb6a76de15a', 'Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 'c2f73e68-d820-46e5-bc37-14ed6d2e8759', '89658392-1ce5-4523-8b08-b7dd9322d57a', 'Amarelo', NOW()),
                           ('6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', 'MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', '2b7607ef-c314-4e75-992f-41fbd1a30a27', '1af3289c-f302-4a95-95f6-c9d05c0aa50b', 'Verde', NOW()),
                           ('901a5875-d0ef-4098-a336-d19f1088538c', 'Kwid', 'Carro compacto da Renault', 88.99, true, 'KLC-0056', 'b306bf4e-ab6d-4c85-8838-dafcc78e1802', '2e46c0aa-011f-4fc2-81e8-5365de43fff7', 'Azul', NOW()),
                           ('82bd1d96-85ac-4559-9ce6-378ef489d078', 'March', 'Carro compacto da Nissan', 148.99, false, 'OPL-456', 'ab42bf2a-0739-4bc5-b901-4890af4a3ec9', '9755db80-c218-4671-b147-22795510c74e', 'Abobora', NOW()),
                           ('c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', 'Corolla', 'Sedan médio da Toyota', 120.50, true, 'DEF-1234', '9483ddf3-3bc2-4ac4-abba-4aa5b59625fb', '345f40dd-f528-4f6a-83c4-2d47b304f557', 'Branco', NOW()),
                           ('06f84056-44f1-40d8-9333-2ed460884b25', 'Mustang', 'Esportivo da Ford', 200.00, false, 'GHJ-5678', '6cf235ae-0759-4ac8-925a-476de5a4f5d0', '62a6ddee-9308-4bbc-a819-5064597383a3', 'Vermelho', NOW()),
                           ('deca7d3e-64b4-4cee-af7b-132c9449f2fb', 'Gol', 'Carro compacto da Volkswagen', 80.00, true, 'IJK-9012', '651d807b-b34f-4818-856d-00bb3ff09d71', '3b0872df-d47a-48f4-964e-b6a099964ee7', 'Prata', NOW()),
                           ('4f2e3bc7-8522-4543-922c-03480d044e62', '488', 'Esportivo da Ferrari', 1500.00, false, 'LMN-3456', '4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe', '146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9', 'Vermelho', NOW()),
                           ('4dafc4f4-5e90-478d-a386-841d74aa368a', 'Civic', 'Sedan médio da Honda', 110.00, true, 'OPQ-7890', '3f831dbb-de3b-4b1a-95dc-602cdeaa7012', 'dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d', 'Prata', NOW());

-- -------------------------------------------------------------------------------------
--                              Inserir aluguel                                       --
-- -------------------------------------------------------------------------------------

CALL insert_rental('55491147-1d2f-455e-9958-1e35f1df5a82', 'f9ed81db-f90a-42d4-b7e4-d554d8f338fd', '2023-03-20', '2023-03-25', 493.85);
CALL insert_rental('01f90d99-85e3-42c0-b55d-1fb6a76de15a', 'ff68e22e-633f-4fe3-b482-590c7163b7e1', '2023-03-21', '2023-03-28', 771.26);
CALL insert_rental('6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', '0723e7a1-ec12-4cdb-b4d5-6169dba540c6', '2023-03-22', '2023-03-27', 346.65);
CALL insert_rental('901a5875-d0ef-4098-a336-d19f1088538c', '9960a044-0f78-4489-8e98-211cb294eee3', '2023-03-23', '2023-03-26', 266.97);
CALL insert_rental('82bd1d96-85ac-4559-9ce6-378ef489d078', '98cf1d75-69c4-4e22-aec4-622c1251d7ed', '2023-03-24', '2023-03-29', 744.95);
CALL insert_rental('c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', 'b7bf613b-6619-4a72-abb6-5dc6734b9ae3', '2023-03-20', '2023-03-25', 446.97);
CALL insert_rental('06f84056-44f1-40d8-9333-2ed460884b25', '5c6d5948-2c06-487c-bd3e-9ae23ded960b', '2023-03-21', '2023-03-28', 1000.00);
CALL insert_rental('deca7d3e-64b4-4cee-af7b-132c9449f2fb', '3b77df60-67df-4071-ac29-abbd451da1c5', '2023-03-22', '2023-03-27', 400.00);
CALL insert_rental('4f2e3bc7-8522-4543-922c-03480d044e62', 'e5a448e3-feaf-4535-9ac0-92b1471de468', '2023-03-23', '2023-03-26', 4500.00);
CALL insert_rental('4dafc4f4-5e90-478d-a386-841d74aa368a', 'a242decb-e589-4ecb-be7d-b7ff6f20ade5', '2023-03-24', '2023-03-29', 550.00);

-- -------------------------------------------------------------------------------------
--                              Inserir especificação para carros                     --
-- -------------------------------------------------------------------------------------

CALL insert_car_specification('55491147-1d2f-455e-9958-1e35f1df5a82', '7eef4f65-96a1-4463-8a7f-3a5df541f310');
CALL insert_car_specification('01f90d99-85e3-42c0-b55d-1fb6a76de15a', '194b82cc-22a3-4058-a9fa-249ad5340268');
CALL insert_car_specification('6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', '3a16cf2b-30d1-4f88-a366-1038746441d4');
CALL insert_car_specification('901a5875-d0ef-4098-a336-d19f1088538c', 'de88412a-d516-47fd-a723-1ec5a13d8b44');
CALL insert_car_specification('82bd1d96-85ac-4559-9ce6-378ef489d078', 'd20d196b-c329-4a32-a76b-cf5802affcfc');
CALL insert_car_specification('c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', 'bcae4e4e-0e7a-41a2-bc24-560077557c0b');
CALL insert_car_specification('06f84056-44f1-40d8-9333-2ed460884b25', '8f4647ee-2ef1-4500-8a28-a0d642665c81');
CALL insert_car_specification('deca7d3e-64b4-4cee-af7b-132c9449f2fb', '7c01a5f0-41b3-4a15-adc5-b0b380bb90e2');
CALL insert_car_specification('4f2e3bc7-8522-4543-922c-03480d044e62', 'ddc7f420-47b1-4f7e-bc35-07765cbd811d');
CALL insert_car_specification('4dafc4f4-5e90-478d-a386-841d74aa368a', '6b83e4cd-ead6-4af0-8e1e-4c332a842717');

-- -------------------------------------------------------------------------------------
--                              Inserir images do carros                              --
-- -------------------------------------------------------------------------------------

CALL insert_car_image('55491147-1d2f-455e-9958-1e35f1df5a82', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('01f90d99-85e3-42c0-b55d-1fb6a76de15a', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('901a5875-d0ef-4098-a336-d19f1088538c', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('82bd1d96-85ac-4559-9ce6-378ef489d078', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('06f84056-44f1-40d8-9333-2ed460884b25', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('deca7d3e-64b4-4cee-af7b-132c9449f2fb', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('4f2e3bc7-8522-4543-922c-03480d044e62', '89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image('4dafc4f4-5e90-478d-a386-841d74aa368a', '89504E470D0A1A0A0000000D49484452000000');