-- -------------------------------------------------------------------------------------
--                              Criação das tabelas                                   --
-- -------------------------------------------------------------------------------------

DROP TABLE IF EXISTS costumer_entity;
CREATE TABLE costumer_entity (
    _id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    driver_license VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    phone_number VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS brand_entity;
CREATE TABLE brand_entity (
    _id SERIAL PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS category_entity;
CREATE TABLE category_entity (
    _id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);


DROP TABLE IF EXISTS specification_entity;
CREATE TABLE specification_entity (
    _id SERIAL PRIMARY KEY,
    specification_name VARCHAR(255) NOT NULL UNIQUE,
    specification_description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS car_entity;
CREATE TABLE car_entity (
    _id SERIAL PRIMARY KEY,
    car_name VARCHAR(255) NOT NULL UNIQUE,
    car_description VARCHAR(30) NOT NULL,
    daily_rate DOUBLE PRECISION NOT NULL,
    car_available BOOLEAN NOT NULL,
    license_plate VARCHAR(30) NOT NULL,
    brand_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    car_color VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brand_entity (_id),
    FOREIGN KEY (category_id) REFERENCES category_entity (_id)
);

DROP TABLE IF EXISTS rental_entity;

CREATE TABLE rental_entity (
    _id SERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL REFERENCES car_entity (_id),
    costumer_id BIGINT NOT NULL REFERENCES costumer_entity (_id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS car_specification;
CREATE TABLE car_specification (
    _id SERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    specification_id BIGINT NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car_entity (_id),
    FOREIGN KEY (specification_id) REFERENCES specification_entity (_id)
);

DROP TABLE IF EXISTS car_image_entity;
CREATE TABLE car_image_entity (
    _id SERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    image BYTEA NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car_entity (_id)
);

-- -------------------------------------------------------------------------------------
--                              Criação de procedures                                 --
-- -------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE insert_costumer(
    p_name VARCHAR(255),
    p_birth_date DATE,
    p_email VARCHAR(255),
    p_driver_license VARCHAR(255),
    p_address VARCHAR(255),
    p_phone_number VARCHAR(255),
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO costumer_entity (name, birth_date, email, driver_license, address, phone_number, created_at, update_at)
    VALUES (p_name, p_birth_date, p_email, p_driver_license, p_address, p_phone_number, NOW(), NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_brand(p_brand_name VARCHAR(255))
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO brand_entity (brand_name, created_at)
    VALUES (p_brand_name, NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_category(p_name VARCHAR(255), p_description TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO category_entity (name, description, created_at)
    VALUES (p_name, p_description, NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_specification(p_specification_name VARCHAR(255), p_specification_description TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO specification_entity (specification_name, specification_description, created_at)
    VALUES (p_specification_name, p_specification_description, NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_car(
    p_car_name VARCHAR(255),
    p_car_description VARCHAR(30),
    p_daily_rate DOUBLE PRECISION,
    p_car_available BOOLEAN,
    p_license_plate VARCHAR(30),
    p_brand_id BIGINT,
    p_category_id BIGINT,
    p_car_color VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO car_entity (car_name, car_description, daily_rate, car_available, license_plate, brand_id, category_id, car_color, created_at)
    VALUES (p_car_name, p_car_description, p_daily_rate, p_car_available, p_license_plate, p_brand_id, p_category_id, p_car_color, NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_rental(
    p_car_id BIGINT,
    p_costumer_id BIGINT,
    p_start_date DATE,
    p_end_date DATE,
    p_total DOUBLE PRECISION
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO rental_entity (car_id, costumer_id, start_date, end_date, total, created_at, update_at)
    VALUES (p_car_id, p_costumer_id, p_start_date, p_end_date, p_total, NOW(), NOW());
END;
$$;

CREATE OR REPLACE PROCEDURE insert_car_specification(
    p_car_id BIGINT,
    p_specification_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO car_specification (car_id, specification_id)
    VALUES (p_car_id, p_specification_id);
END;
$$;

CREATE OR REPLACE PROCEDURE insert_car_image(
    p_car_id BIGINT,
    p_image BYTEA
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO car_image_entity (car_id, image, created_at)
    VALUES (p_car_id, p_image, NOW());
END;
$$;
