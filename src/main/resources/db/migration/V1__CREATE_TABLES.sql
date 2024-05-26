CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer_entity
(
    _id            UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    birthdate      DATE         NOT NULL,
    email          VARCHAR(255) NOT NULL UNIQUE,
    driver_license VARCHAR(255) NOT NULL UNIQUE,
    address        VARCHAR(255),
    phone_number   VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL
);

CREATE TABLE brand_entity
(
    _id        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);

CREATE TABLE category_entity
(
    _id         UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT         NOT NULL,
    created_at  TIMESTAMP    NOT NULL
);


CREATE TABLE specification_entity
(
    _id                       UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    specification_name        VARCHAR(255) NOT NULL UNIQUE,
    specification_description TEXT         NOT NULL,
    created_at                TIMESTAMP    NOT NULL
);

CREATE TABLE car_entity
(
    _id             UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    car_name        VARCHAR(255)     NOT NULL UNIQUE,
    car_description VARCHAR(30)      NOT NULL,
    daily_rate      DOUBLE PRECISION NOT NULL,
    car_available   BOOLEAN          NOT NULL,
    license_plate   VARCHAR(30)      NOT NULL,
    brand_id        UUID             NOT NULL,
    category_id     UUID             NOT NULL,
    car_color       VARCHAR(255)     NOT NULL,
    created_at      TIMESTAMP        NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brand_entity (_id),
    FOREIGN KEY (category_id) REFERENCES category_entity (_id)
);

CREATE TABLE rental_entity
(
    _id         UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    car_id      UUID             NOT NULL,
    customer_id UUID             NOT NULL,
    start_date  DATE             NOT NULL,
    end_date    DATE             NOT NULL,
    total       DOUBLE PRECISION NOT NULL,
    created_at  TIMESTAMP        NOT NULL,
    updated_at  TIMESTAMP        NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car_entity (_id),
    FOREIGN KEY (customer_id) REFERENCES customer_entity (_id)
);

CREATE TABLE car_specification
(
    _id              UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    car_id           UUID NOT NULL,
    specification_id UUID NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car_entity (_id),
    FOREIGN KEY (specification_id) REFERENCES specification_entity (_id)
);

CREATE TABLE car_image_entity
(
    _id        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    car_id     UUID         NOT NULL,
    image      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car_entity (_id)
);

CREATE TABLE user_entity
(
    _id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email      VARCHAR(255) NOT NULL UNIQUE,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);
