-- -------------------------------------------------------------------------------------
--                              Inserir clientes                                      --
-- -------------------------------------------------------------------------------------

INSERT INTO customer_entity VALUES
('f9ed81db-f90a-42d4-b7e4-d554d8f338fd', 'Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', '2021-07-21T12:05:17.147971', '2023-10-21T12:05:17.147971'),
('ff68e22e-633f-4fe3-b482-590c7163b7e1', 'Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', '2023-12-21T14:01:19.437572', '2023-10-21T12:05:17.147971'),
('0723e7a1-ec12-4cdb-b4d5-6169dba540c6', 'Heloisa Aurora Jessica dos Santos', '1988-08-12', 'heloisaauroradossantos@sha.com.br', '18895846006', 'Rua Astrogildo de Almeida, 328', '(47) 98101-9804', '2019-11-09T21:21:39.537270', '2022-11-29T23:31:19.537270'),
('9960a044-0f78-4489-8e98-211cb294eee3', 'Julio Marcelo Nelson Melo', '1974-03-05', 'julio.marcelo.melo@attglobal.net', '90897130198', 'Praça Vereador Osvaldo Mendonça, 752', '(79) 99539-6592', '2020-09-11T23:12:21.156273', '2022-11-11T21:36:11.156273'),
('98cf1d75-69c4-4e22-aec4-622c1251d7ed', 'Hugo Benjamin Barbosa', '1980-11-02', 'hugo.benjamin.barbosa@cathedranet.com.br', '74091777742', 'Travessa Jasmim, 101', '(68) 99602-7578', '2023-07-11T09:05:17.157661', '2023-11-14T09:10:33.157661'),
('b7bf613b-6619-4a72-abb6-5dc6734b9ae3', 'Marta Ferreira Castro', '1995-06-22', 'martafc@gmail.com', '00230558184', 'Rua das Flores, 45', '(11) 98765-4321', '2022-07-09T13:01:19.138071', '2023-10-19T15:35:26.138071'),
('5c6d5948-2c06-487c-bd3e-9ae23ded960b', 'Pedro Henrique Silva', '1989-03-15', 'pedro.henrique.silva@outlook.com', '03482045429', 'Avenida Brasil, 2345', '(21) 99876-5432', '2023-11-12T17:01:33.268101', '2024-03-11T16:01:12.268101'),
('3b77df60-67df-4071-ac29-abbd451da1c5', 'Luana Oliveira Costa', '1991-12-05', 'luanaoliveiracosta@gmail.com', '03780614280', 'Rua São Paulo, 123', '(31) 98765-4321', '2016-01-01T11:11:29.615971', '2018-01-12T15:33:24.615971'),
('e5a448e3-feaf-4535-9ac0-92b1471de468', 'Fernando Henrique Ribeiro', '1985-05-11', 'fernando.hr@gmail.com', '01234567890', 'Rua das Palmeiras, 67', '(41) 99876-5432', '2024-03-04T21:21:39.256777', '2024-03-06T11:23:11.256777'),
('a242decb-e589-4ecb-be7d-b7ff6f20ade5', 'Marcela Souza Santos', '1992-09-29', 'marcela.ss@gmail.com', '12345678901', 'Avenida Paulista, 1000', '(11) 98765-4321', '2018-03-12T12:11:11.237971', '2022-11-23T17:33:57.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir marca                                         --
-- -------------------------------------------------------------------------------------

INSERT INTO brand_entity VALUES
('51208e86-1f63-4e18-ae59-941fd5e342cf', 'BMW', false, '2021-07-21T12:05:17.147971', '2023-10-21T12:05:17.147971'),
('c2f73e68-d820-46e5-bc37-14ed6d2e8759', 'Chevrolet', false, '2023-12-21T14:01:19.437572', '2023-10-21T12:05:17.147971'),
('2b7607ef-c314-4e75-992f-41fbd1a30a27', 'Yamaha', false, '2019-11-09T21:21:39.537270', '2022-11-29T23:31:19.537270'),
('b306bf4e-ab6d-4c85-8838-dafcc78e1802', 'Renault', false, '2020-09-11T23:12:21.156273', '2022-11-11T21:36:11.156273'),
('ab42bf2a-0739-4bc5-b901-4890af4a3ec9', 'Nissan', false, '2023-07-11T09:05:17.157661', '2023-11-14T09:10:33.157661'),
('9483ddf3-3bc2-4ac4-abba-4aa5b59625fb', 'Toyota', false, '2022-07-09T13:01:19.138071', '2023-10-19T15:35:26.138071'),
('6cf235ae-0759-4ac8-925a-476de5a4f5d0', 'Ford', false, '2023-11-12T17:01:33.268101', '2024-03-11T16:01:12.268101'),
('651d807b-b34f-4818-856d-00bb3ff09d71', 'Volkswagen', false, '2016-01-01T11:11:29.615971', '2018-01-12T15:33:24.615971'),
('4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe', 'Ferrari', false, '2024-03-04T21:21:39.256777', '2024-03-06T11:23:11.256777'),
('3f831dbb-de3b-4b1a-95dc-602cdeaa7012', 'Honda', false, '2018-03-12T12:11:11.237971', '2022-11-23T17:33:57.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir categoria                                     --
-- -------------------------------------------------------------------------------------

INSERT INTO category_entity VALUES
('1deb1e69-6b1a-4d88-9a1b-a9889398b54a', 'SUVs', 'Veiculos utilitarios esportivos', '2023-10-21T12:05:17.147971'),
('89658392-1ce5-4523-8b08-b7dd9322d57a', 'Carros Eletricos', 'Veiculos movidos a energia eletrica', '2023-10-21T12:05:17.147971'),
('1af3289c-f302-4a95-95f6-c9d05c0aa50b', 'Hatch', 'Categoria de carros com carroceria hatchback', '2022-11-29T23:31:19.537270'),
('2e46c0aa-011f-4fc2-81e8-5365de43fff7', 'Picape', 'Categoria de carros com carroceria pickup', '2022-11-11T21:36:11.156273'),
('9755db80-c218-4671-b147-22795510c74e', 'Esportivo', 'Categoria de carros esportivos com alta performance', '2023-11-14T09:10:33.157661'),
('345f40dd-f528-4f6a-83c4-2d47b304f557', 'Caminhoes', 'veiculos de grande porte para transporte de cargas', '2023-10-19T15:35:26.138071'),
('62a6ddee-9308-4bbc-a819-5064597383a3', 'Carros de Luxo', 'Carros de alta qualidade e com grande valor agregado', '2024-03-11T16:01:12.268101'),
('3b0872df-d47a-48f4-964e-b6a099964ee7', 'Minivans', 'Veiculos familiares com grande espaço interno', '2018-01-12T15:33:24.615971'),
('146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9', 'Coupe', 'Categoria de carros com carroceria coupe', '2024-03-06T11:23:11.256777'),
('dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d', 'Carros Antigos', 'Veiculos com mais de 30 anos de fabricacao', '2022-11-23T17:33:57.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir especificação                                 --
-- -------------------------------------------------------------------------------------

INSERT INTO specification_entity VALUES
('7eef4f65-96a1-4463-8a7f-3a5df541f310','Motor', 'Especificacao tecnica que define o tipo e a potencia do motor do veiculo.', '2021-07-21T12:05:17.147971'),
('194b82cc-22a3-4058-a9fa-249ad5340268', 'Cambio', 'Especificacao tecnica que define o tipo de transmissao do veiculo.', '2023-12-21T14:01:19.437572'),
('3a16cf2b-30d1-4f88-a366-1038746441d4', 'Direcao', 'Especificacao tecnica que define o tipo de direcao do veiculo.', '2019-11-09T21:21:39.537270'),
('de88412a-d516-47fd-a723-1ec5a13d8b44', 'Freios', 'Especificacao tecnica que define o tipo de sistema de freios do veiculo.', '2020-09-11T23:12:21.156273'),
('d20d196b-c329-4a32-a76b-cf5802affcfc', 'Suspensao', 'Especificacao tecnica que define o tipo de suspensao do veiculo.', '2023-07-11T09:05:17.157661'),
('bcae4e4e-0e7a-41a2-bc24-560077557c0b', 'Capacidade do tanque', 'Especificacao tecnica que define a capacidade do tanque de combustivel do veiculo.', '2022-07-09T13:01:19.138071'),
('8f4647ee-2ef1-4500-8a28-a0d642665c81', 'Peso', 'Especificacao tecnica que define o peso do veiculo.', '2024-08-11T16:01:33.268101'),
('7c01a5f0-41b3-4a15-adc5-b0b380bb90e2', 'Comprimento', 'Especificacao tecnica que define o comprimento do veiculo.', '2016-01-01T11:11:29.615971'),
('ddc7f420-47b1-4f7e-bc35-07765cbd811d', 'Altura', 'Especificacao tecnica que define a altura do veiculo.', '2024-03-04T21:21:39.256777'),
('6b83e4cd-ead6-4af0-8e1e-4c332a842717', 'Largura', 'Especificacao tecnica que define a largura do veiculo.', '2018-03-12T12:11:11.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir carro                                         --
-- -------------------------------------------------------------------------------------

INSERT INTO car_entity VALUES
('55491147-1d2f-455e-9958-1e35f1df5a82', 'Serie 3', 'Sedan medio da BMW', 98.77, true, 'XPT-1146', '51208e86-1f63-4e18-ae59-941fd5e342cf', '1deb1e69-6b1a-4d88-9a1b-a9889398b54a', 'Preto', '2023-10-21T12:05:17.147971'),
('01f90d99-85e3-42c0-b55d-1fb6a76de15a', 'Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 'c2f73e68-d820-46e5-bc37-14ed6d2e8759', '89658392-1ce5-4523-8b08-b7dd9322d57a', 'Amarelo', '2024-03-11T16:15:37.147971'),
('6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', 'MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', '2b7607ef-c314-4e75-992f-41fbd1a30a27', '1af3289c-f302-4a95-95f6-c9d05c0aa50b', 'Verde', '2022-11-29T23:31:19.537270'),
('901a5875-d0ef-4098-a336-d19f1088538c', 'Kwid', 'Carro compacto da Renault', 88.99, true, 'KLC-0056', 'b306bf4e-ab6d-4c85-8838-dafcc78e1802', '2e46c0aa-011f-4fc2-81e8-5365de43fff7', 'Azul', '2022-11-11T21:36:11.156273'),
('82bd1d96-85ac-4559-9ce6-378ef489d078', 'March', 'Carro compacto da Nissan', 148.99, false, 'OPL-456', 'ab42bf2a-0739-4bc5-b901-4890af4a3ec9', '9755db80-c218-4671-b147-22795510c74e', 'Abobora', '2023-11-14T09:10:33.157661'),
('c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', 'Corolla', 'Sedan medio da Toyota', 120.50, true, 'DEF-1234', '9483ddf3-3bc2-4ac4-abba-4aa5b59625fb', '345f40dd-f528-4f6a-83c4-2d47b304f557', 'Branco', '2023-10-19T15:35:26.138071'),
('06f84056-44f1-40d8-9333-2ed460884b25', 'Mustang', 'Esportivo da Ford', 200.00, false, 'GHJ-5678', '6cf235ae-0759-4ac8-925a-476de5a4f5d0', '62a6ddee-9308-4bbc-a819-5064597383a3', 'Vermelho', '2024-03-11T16:01:12.268101'),
('deca7d3e-64b4-4cee-af7b-132c9449f2fb', 'Gol', 'Carro compacto da Volkswagen', 80.00, true, 'IJK-9012', '651d807b-b34f-4818-856d-00bb3ff09d71', '3b0872df-d47a-48f4-964e-b6a099964ee7', 'Prata', '2018-01-12T15:33:24.615971'),
('4f2e3bc7-8522-4543-922c-03480d044e62', '488', 'Esportivo da Ferrari', 1500.00, false, 'LMN-3456', '4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe', '146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9', 'Vermelho', '2024-03-06T11:23:11.256777'),
('4dafc4f4-5e90-478d-a386-841d74aa368a', 'Civic', 'Sedan medio da Honda', 110.00, true, 'OPQ-7890', '3f831dbb-de3b-4b1a-95dc-602cdeaa7012', 'dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d', 'Prata', '2022-11-23T17:33:57.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir aluguel                                       --
-- -------------------------------------------------------------------------------------

INSERT INTO rental_entity VALUES
('f944cfcc-55b6-4c80-be50-dee4ee288fce','55491147-1d2f-455e-9958-1e35f1df5a82','f9ed81db-f90a-42d4-b7e4-d554d8f338fd','2023-03-20','2023-03-25',493.85,'2021-07-21T12:05:17.147971', '2023-10-21T12:05:17.147971'),
('01c31ec9-1184-4cda-b82f-34cce080aa17','01f90d99-85e3-42c0-b55d-1fb6a76de15a','ff68e22e-633f-4fe3-b482-590c7163b7e1','2023-03-21','2023-03-28',771.26,'2023-12-21T14:01:19.437572', '2023-10-21T12:05:17.147971'),
('1a3c1185-3639-447c-922d-e678591448ec','6cebea9d-5fd0-4ba7-80f8-3903187d3b4e','0723e7a1-ec12-4cdb-b4d5-6169dba540c6','2023-03-22','2023-03-27',346.65,'2019-11-09T21:21:39.537270', '2022-11-29T23:31:19.537270'),
('b428e639-12d2-4293-9e40-8497882f59c2','901a5875-d0ef-4098-a336-d19f1088538c','9960a044-0f78-4489-8e98-211cb294eee3','2023-03-23','2023-03-26',266.97,'2020-09-11T23:12:21.156273', '2022-11-11T21:36:11.156273'),
('76a6755e-112b-4b98-ab3a-5321bc2b369f','82bd1d96-85ac-4559-9ce6-378ef489d078','98cf1d75-69c4-4e22-aec4-622c1251d7ed','2023-03-24','2023-03-29',744.95,'2023-07-11T09:05:17.157661', '2023-11-14T09:10:33.157661'),
('acc6e1e8-843f-4a47-842e-eebb67679922','c8d4c641-4b14-4736-8e60-bdc01d5ee8fe','b7bf613b-6619-4a72-abb6-5dc6734b9ae3','2023-03-20','2023-03-25',446.97,'2022-07-09T13:01:19.138071', '2023-10-19T15:35:26.138071'),
('ee8a3e67-71ee-49b5-be56-0461f9373515','06f84056-44f1-40d8-9333-2ed460884b25','5c6d5948-2c06-487c-bd3e-9ae23ded960b','2023-03-21','2023-03-28',1000,'2023-11-12T17:01:33.268101', '2024-03-11T16:01:12.268101'),
('fa6feddd-c29a-4ac9-9fe3-8ad32ccc82c9','deca7d3e-64b4-4cee-af7b-132c9449f2fb','3b77df60-67df-4071-ac29-abbd451da1c5','2023-03-22','2023-03-27',400,'2016-01-01T11:11:29.615971', '2018-01-12T15:33:24.615971'),
('d87ffed7-692c-4da7-8dfd-5e7d240b1a96','4f2e3bc7-8522-4543-922c-03480d044e62','e5a448e3-feaf-4535-9ac0-92b1471de468','2023-03-23','2023-03-26',4500,'2024-03-04T21:21:39.256777', '2024-03-06T11:23:11.256777'),
('53d0f387-82d5-433e-b64d-cfe50e7cf6e4','4dafc4f4-5e90-478d-a386-841d74aa368a','a242decb-e589-4ecb-be7d-b7ff6f20ade5','2023-03-24','2023-03-29',550,'2018-03-12T12:11:11.237971', '2022-11-23T17:33:57.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir Especificacao para carros                     --
-- -------------------------------------------------------------------------------------

INSERT INTO car_specification VALUES
('89092af1-f0c2-410a-8dc9-ba7a6320261e','55491147-1d2f-455e-9958-1e35f1df5a82', '7eef4f65-96a1-4463-8a7f-3a5df541f310'),
('a0d1f278-e906-4c93-a3c8-9c3b637fbde4', '01f90d99-85e3-42c0-b55d-1fb6a76de15a', '194b82cc-22a3-4058-a9fa-249ad5340268'),
('cd6e8f3f-5c68-4a7e-bece-fe7d9b59e2b3', '6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', '3a16cf2b-30d1-4f88-a366-1038746441d4'),
('d43860ac-c116-4712-a8fb-dec8bdc34d64', '901a5875-d0ef-4098-a336-d19f1088538c', 'de88412a-d516-47fd-a723-1ec5a13d8b44'),
('0493ec2c-10de-445e-a5af-fb8368f9745e', '82bd1d96-85ac-4559-9ce6-378ef489d078', 'd20d196b-c329-4a32-a76b-cf5802affcfc'),
('edabe532-d3d5-40fd-b2b6-e6a63487bbe1', 'c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', 'bcae4e4e-0e7a-41a2-bc24-560077557c0b'),
('68aec737-09f8-4dc0-91c0-722ef0b40a25', '06f84056-44f1-40d8-9333-2ed460884b25', '8f4647ee-2ef1-4500-8a28-a0d642665c81'),
('415d8f74-0654-4427-8a16-3ce01c15bb33', 'deca7d3e-64b4-4cee-af7b-132c9449f2fb', '7c01a5f0-41b3-4a15-adc5-b0b380bb90e2'),
('eb29e137-407b-42c9-be0b-fccb2f6a0c95', '4f2e3bc7-8522-4543-922c-03480d044e62', 'ddc7f420-47b1-4f7e-bc35-07765cbd811d'),
('c93d5c92-efd1-4f8e-ad22-1fd807104b81', '4dafc4f4-5e90-478d-a386-841d74aa368a', '6b83e4cd-ead6-4af0-8e1e-4c332a842717');

-- -------------------------------------------------------------------------------------
--                              Inserir images do carros                              --
-- -------------------------------------------------------------------------------------

INSERT INTO car_image_entity VALUES
('ba091788-8edb-4a5a-bab2-2c8dade5addf','55491147-1d2f-455e-9958-1e35f1df5a82', '89504E470D0A1A0A0000000D49484452000000', '2021-07-21T12:05:17.147971'),
('8bafe033-b9d2-4051-9bf6-45476c015197', '01f90d99-85e3-42c0-b55d-1fb6a76de15a', '89504E470D0A1A0A0000000D49484452000000', '2023-12-21T14:01:19.437572'),
('3856093c-6be9-4ed2-9bcc-3de10642aeda', '6cebea9d-5fd0-4ba7-80f8-3903187d3b4e', '89504E470D0A1A0A0000000D49484452000000', '2019-11-09T21:21:39.537270'),
('fd629e5f-24ab-4e60-a32b-366dd3680cea', '901a5875-d0ef-4098-a336-d19f1088538c', '89504E470D0A1A0A0000000D49484452000000', '2020-09-11T23:12:21.156273'),
('950fd08d-bae6-4a39-89ae-6d64ccdd5678', '82bd1d96-85ac-4559-9ce6-378ef489d078', '89504E470D0A1A0A0000000D49484452000000', '2023-07-11T09:05:17.157661'),
('e9cae96e-5201-420c-a277-3dc2ad1e2deb', 'c8d4c641-4b14-4736-8e60-bdc01d5ee8fe', '89504E470D0A1A0A0000000D49484452000000', '2022-07-09T13:01:19.138071'),
('0e84d624-9012-4776-b38a-f877543e4821', '06f84056-44f1-40d8-9333-2ed460884b25', '89504E470D0A1A0A0000000D49484452000000', '2024-08-11T16:01:33.268101'),
('7818608c-4d7b-44ec-89b0-03398f0a0f8a', 'deca7d3e-64b4-4cee-af7b-132c9449f2fb', '89504E470D0A1A0A0000000D49484452000000', '2016-01-01T11:11:29.615971'),
('9a1ec594-dec5-4422-bf10-719864877aaf', '4f2e3bc7-8522-4543-922c-03480d044e62', '89504E470D0A1A0A0000000D49484452000000', '2024-03-04T21:21:39.256777'),
('97f67782-ead0-4948-b550-368a6917eac4', '4dafc4f4-5e90-478d-a386-841d74aa368a', '89504E470D0A1A0A0000000D49484452000000', '2018-03-12T12:11:11.237971');

-- -------------------------------------------------------------------------------------
--                              Inserir Usuarios                                      --
--                           Todas as senhas sao '12345'                              --
-- -------------------------------------------------------------------------------------
INSERT INTO user_entity VALUES
('9d63e233-729c-46cf-80d4-0032fe559123','feufumafriceu-5535@yopmail.com', 'cleu', '$2a$10$wuu3XKBUcU2aUjmjDmcbe.jpA6DAw.PaTK.uAb7dn8cSzg5UKJXJq', 'SUPER', '2021-07-21T12:05:17.147971', '2023-10-21T12:05:17.147971');