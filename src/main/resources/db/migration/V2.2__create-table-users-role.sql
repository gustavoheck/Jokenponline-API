CREATE TABLE auth.users_role (
    id_users BIGINT,
    id_role BIGINT,

    PRIMARY KEY (id_users, id_role),
    FOREIGN KEY (id_users) REFERENCES auth.users(id) ON DELETE CASCADE,
    FOREIGN KEY (id_role) REFERENCES auth.role(id) ON DELETE CASCADE
);