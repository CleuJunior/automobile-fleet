-- -------------------------------------------------------------------------------------
--                              Use this to restart the script sql                    --
-- -------------------------------------------------------------------------------------

DROP TABLE IF EXISTS flyway_schema_history;
DROP TABLE IF EXISTS car_specification CASCADE;
DROP TABLE IF EXISTS car_image_entity CASCADE;
DROP TABLE IF EXISTS rental_entity CASCADE;
DROP TABLE IF EXISTS car_entity CASCADE;
DROP TABLE IF EXISTS costumer_entity CASCADE;
DROP TABLE IF EXISTS brand_entity CASCADE;
DROP TABLE IF EXISTS category_entity CASCADE;
DROP TABLE IF EXISTS specification_entity CASCADE;

DROP PROCEDURE IF EXISTS insert_costumer;
DROP PROCEDURE IF EXISTS insert_brand;
DROP PROCEDURE IF EXISTS insert_category;
DROP PROCEDURE IF EXISTS insert_specification;
DROP PROCEDURE IF EXISTS insert_car;
DROP PROCEDURE IF EXISTS insert_rental;
DROP PROCEDURE IF EXISTS insert_car_specification;
DROP PROCEDURE IF EXISTS insert_car_image;