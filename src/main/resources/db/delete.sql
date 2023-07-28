-- -------------------------------------------------------------------------------------
--                              Use this to restart the script sql                    --
-- -------------------------------------------------------------------------------------

DROP TABLE IF EXISTS car_specification;
DROP TABLE IF EXISTS car_image_entity;
DROP TABLE IF EXISTS rental_entity;
DROP TABLE IF EXISTS car_entity;
DROP TABLE IF EXISTS costumer_entity;
DROP TABLE IF EXISTS brand_entity;
DROP TABLE IF EXISTS category_entity;
DROP TABLE IF EXISTS specification_entity;
DROP PROCEDURE IF EXISTS insert_costumer;
DROP PROCEDURE IF EXISTS insert_brand;
DROP PROCEDURE IF EXISTS insert_category;
DROP PROCEDURE IF EXISTS insert_specification;
DROP PROCEDURE IF EXISTS insert_car;
DROP PROCEDURE IF EXISTS insert_rental;
DROP PROCEDURE IF EXISTS insert_car_specification;
DROP PROCEDURE IF EXISTS insert_car_image;