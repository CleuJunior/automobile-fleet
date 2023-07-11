DROP VIEW IF EXISTS car_brand_category;
CREATE VIEW car_brand_category AS
SELECT b.brand_name, c.car_name, c.car_description, c.daily_rate, c.car_available AS available, c.license_plate, cat.name AS category_name, cat.description
FROM car_entity c
INNER JOIN brand_entity b ON c.brand_id = b._id
INNER JOIN category_entity cat ON c.category_id = cat._id;


CREATE VIEW car_specification_infos AS
SELECT cs._id as car_specifications_id, c.car_name as car_name, c.license_plate, s.specification_name as specification, s.specification_description
FROM car_specification cs
INNER JOIN car_entity c ON cs._id = c._id
INNER JOIN specification_entity s ON cs._id = s._id;
