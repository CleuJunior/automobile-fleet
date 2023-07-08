-- -------------------------------------------------------------------------------------
--                              Inserir clientes                                      --
-- -------------------------------------------------------------------------------------

CALL insert_costumer('Raimunda Regina Porto', '1974-03-05', 'raimundareginaporto@clinicasilhouette.com.br', '08493447718', 'Rua Astrogildo de Almeida, 328', '(73) 99362-1339', '2022-12-21 21:13:12', '2023-02-18 09:31:00');
CALL insert_costumer('Gustavo Rafael Elias da Mata', '1960-10-03', 'gustavo-damata84@vipsaude.com.br', '02911058331', 'Jardim Novo Niterói, 378', '(65) 98238-1865', '2022-08-13 18:08:30', '2023-03-18 13:11:09');
CALL insert_costumer('Heloisa Aurora Jéssica dos Santos', '1988-08-12', 'heloisaauroradossantos@sha.com.br', '18895846006', 'Rua Astrogildo de Almeida, 328', '(47) 98101-9804', '2023-01-21 10:18:20', '2023-03-24 21:44:11');
CALL insert_costumer('Julio Marcelo Nelson Melo', '1974-03-05', 'julio.marcelo.melo@attglobal.net', '90897130198', 'Praça Vereador Osvaldo Mendonça, 752', '(79) 99539-6592', '2022-02-21 23:08:23', '2022-12-11 08:31:33');
CALL insert_costumer('Hugo Benjamin Barbosa', '1980-11-02', 'hugo.benjamin.barbosa@cathedranet.com.br', '74091777742', 'Travessa Jasmim, 101', '(68) 99602-7578', '2022-12-03 09:23:14', '2023-02-18 14:31:57');
CALL insert_costumer('Marta Ferreira Castro', '1995-06-22', 'martafc@gmail.com', '00230558184', 'Rua das Flores, 45', '(11) 98765-4321', '2022-09-18 11:22:33', '2023-03-21 16:55:44');
CALL insert_costumer('Pedro Henrique Silva', '1989-03-15', 'pedro.henrique.silva@outlook.com', '03482045429', 'Avenida Brasil, 2345', '(21) 99876-5432', '2022-10-20 15:42:00', '2023-03-22 09:11:22');
CALL insert_costumer('Luana Oliveira Costa', '1991-12-05', 'luanaoliveiracosta@gmail.com', '03780614280', 'Rua São Paulo, 123', '(31) 98765-4321', '2022-11-11 08:15:45', '2023-03-23 14:33:00');
CALL insert_costumer('Fernando Henrique Ribeiro', '1985-05-11', 'fernando.hr@gmail.com', '01234567890', 'Rua das Palmeiras, 67', '(41) 99876-5432', '2022-12-01 12:30:15', '2023-03-24 11:22:33');
CALL insert_costumer('Marcela Souza Santos', '1992-09-29', 'marcela.ss@gmail.com', '12345678901', 'Avenida Paulista, 1000', '(11) 98765-4321', '2023-01-02 18:05:00', '2023-03-24 16:44:11');

-- -------------------------------------------------------------------------------------
--                              Inserir marca                                         --
-- -------------------------------------------------------------------------------------

CALL insert_brand('BMW');
CALL insert_brand('Chevrolet');
CALL insert_brand('Yamaha');
CALL insert_brand('Renault');
CALL insert_brand('Nissan');
CALL insert_brand('Toyota');
CALL insert_brand('Ford');
CALL insert_brand('Volkswagen');
CALL insert_brand('Ferrari');
CALL insert_brand('Honda');

-- -------------------------------------------------------------------------------------
--                              Inserir categoria                                     --
-- -------------------------------------------------------------------------------------

CALL insert_category('SUVs', 'Veículos utilitários esportivos');
CALL insert_category('Carros Elétricos', 'Veículos movidos a energia elétrica');
CALL insert_category('Hatch', 'Categoria de carros com carroceria hatchback');
CALL insert_category('Picape', 'Categoria de carros com carroceria pickup');
CALL insert_category('Esportivo', 'Categoria de carros esportivos com alta performance');
CALL insert_category('Caminhões', 'Veículos de grande porte para transporte de cargas');
CALL insert_category('Carros de Luxo', 'Carros de alta qualidade e com grande valor agregado');
CALL insert_category('Minivans', 'Veículos familiares com grande espaço interno');
CALL insert_category('Coupé', 'Categoria de carros com carroceria coupé');
CALL insert_category('Carros Antigos', 'Veículos com mais de 30 anos de fabricação');

-- -------------------------------------------------------------------------------------
--                              Inserir especificação                                 --
-- -------------------------------------------------------------------------------------

CALL insert_specification('Motor', 'Especificação técnica que define o tipo e a potência do motor do veículo.');
CALL insert_specification('Câmbio', 'Especificação técnica que define o tipo de transmissão do veículo.');
CALL insert_specification('Direção', 'Especificação técnica que define o tipo de direção do veículo.');
CALL insert_specification('Freios', 'Especificação técnica que define o tipo de sistema de freios do veículo.');
CALL insert_specification('Suspensão', 'Especificação técnica que define o tipo de suspensão do veículo.');
CALL insert_specification('Capacidade do tanque', 'Especificação técnica que define a capacidade do tanque de combustível do veículo.');
CALL insert_specification('Peso', 'Especificação técnica que define o peso do veículo.');
CALL insert_specification('Comprimento', 'Especificação técnica que define o comprimento do veículo.');
CALL insert_specification('Altura', 'Especificação técnica que define a altura do veículo.');
CALL insert_specification('Largura', 'Especificação técnica que define a largura do veículo.');

-- -------------------------------------------------------------------------------------
--                              Inserir carro                                         --
-- -------------------------------------------------------------------------------------

CALL insert_car('Série 3', 'Sedan médio da BMW', 98.77, true, 'XPT-1146', 1, 1, 'Preto');
CALL insert_car('Onix', 'Carro popular da Chevrolet', 110.18, false, 'ABC-1456', 2, 2, 'Amarelo');
CALL insert_car('MT-09', 'Moto naked da Yamaha', 69.33, false, 'XYZ-4944', 3, 3, 'Verde');
CALL insert_car('Kwid', 'Carro compacto da Renault', 88.99, true, 'KLC-0056', 4, 4, 'Azul');
CALL insert_car('March', 'Carro compacto da Nissan', 148.99, false, 'OPL-456', 5, 5, 'Abobora');
CALL insert_car('Corolla', 'Sedan médio da Toyota', 120.50, true, 'DEF-1234', 6, 6, 'Branco');
CALL insert_car('Mustang', 'Esportivo da Ford', 200.00, false, 'GHJ-5678', 7, 7, 'Vermelho');
CALL insert_car('Gol', 'Carro compacto da Volkswagen', 80.00, true, 'IJK-9012', 8, 8, 'Prata');
CALL insert_car('488', 'Esportivo da Ferrari', 1500.00, false, 'LMN-3456', 9, 9, 'Vermelho');
CALL insert_car('Civic', 'Sedan médio da Honda', 110.00, true, 'OPQ-7890', 10, 10, 'Prata');


-- -------------------------------------------------------------------------------------
--                              Inserir aluguel                                       --
-- -------------------------------------------------------------------------------------

CALL insert_rental(1, 1, '2023-03-20', '2023-03-25', 493.85);
CALL insert_rental(2, 2, '2023-03-21', '2023-03-28', 771.26);
CALL insert_rental(3, 3, '2023-03-22', '2023-03-27', 346.65);
CALL insert_rental(4, 4, '2023-03-23', '2023-03-26', 266.97);
CALL insert_rental(5, 5, '2023-03-24', '2023-03-29', 744.95);
CALL insert_rental(6, 6, '2023-03-20', '2023-03-25', 446.97);
CALL insert_rental(7, 7, '2023-03-21', '2023-03-28', 1000.00);
CALL insert_rental(8, 8, '2023-03-22', '2023-03-27', 400.00);
CALL insert_rental(9, 9, '2023-03-23', '2023-03-26', 4500.00);
CALL insert_rental(10, 10, '2023-03-24', '2023-03-29', 550.00);

-- -------------------------------------------------------------------------------------
--                              Inserir especificação para carros                     --
-- -------------------------------------------------------------------------------------

CALL insert_car_specification(1, 1);
CALL insert_car_specification(2, 2);
CALL insert_car_specification(3, 3);
CALL insert_car_specification(4, 4);
CALL insert_car_specification(5, 5);
CALL insert_car_specification(6, 6);
CALL insert_car_specification(7, 7);
CALL insert_car_specification(8, 8);
CALL insert_car_specification(9, 9);
CALL insert_car_specification(10, 10);

-- -------------------------------------------------------------------------------------
--                              Inserir images do carros                              --
-- -------------------------------------------------------------------------------------

CALL insert_car_image(1, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(2, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(3, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(4, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(5, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(6, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(7, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(8, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(9, '\x89504E470D0A1A0A0000000D49484452000000');
CALL insert_car_image(10, '\x89504E470D0A1A0A0000000D49484452000000');

