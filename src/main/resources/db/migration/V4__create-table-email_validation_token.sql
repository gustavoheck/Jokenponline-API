CREATE TABLE auth.email_validation_token (
    token UUID PRIMARY KEY,
    expiration_time DATE NOT NULL,
    id_users BIGINT UNIQUE NOT NULL,

    FOREIGN KEY (id_users) REFERENCES auth.users(id) ON DELETE CASCADE
);