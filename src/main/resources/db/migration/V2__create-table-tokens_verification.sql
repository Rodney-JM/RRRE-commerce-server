CREATE TABLE verifications_tokens(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    token VARCHAR(6) NOT NULL,

    PRIMARY KEY(id)
);