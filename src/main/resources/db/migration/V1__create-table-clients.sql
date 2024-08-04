CREATE TABLE clients(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATE,
    verified TINYINT(1) NOT NULL DEFAULT 0,

    PRIMARY KEY(id)
);