CREATE TABLE auth.users (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID UNIQUE NOT NULL,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);