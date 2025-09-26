-- 1. Elimina la tabla actual
DROP TABLE IF EXISTS users CASCADE; 

-- 2. Vuelve a crear la tabla con los ajustes correctos (BIGSERIAL y VARCHAR(255))
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id BIGSERIAL,
    uuid VARCHAR(100) DEFAULT uuid_generate_v4()::text,
    picture_name VARCHAR(255),
    name_title VARCHAR(80),
    first_name VARCHAR(30), 
    last_name VARCHAR(50),
    num_tel VARCHAR(20),
    gender VARCHAR(8),
    email_address VARCHAR(255), 
    state VARCHAR(30),
    city VARCHAR(30),
    postcode INT
);
