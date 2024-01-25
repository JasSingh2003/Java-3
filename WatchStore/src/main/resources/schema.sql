CREATE TABLE watches (
                         watchId BIGINT AUTO_INCREMENT PRIMARY KEY,
                         brand VARCHAR(25),
                         price NUMERIC(6,2),
                         stockQuantity INT,
                         description VARCHAR(100),
                         rating NUMERIC(2,1)
);

CREATE TABLE cart
(
    brand VARCHAR(25),
    price NUMERIC(6,2),
    rating NUMERIC(2,1)
);

CREATE TABLE users(
    userId BIGINT NOT NULL PRIMARY KEY
    AUTO_INCREMENT,
    email VARCHAR(75) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(128) NOT NULL,
    enabled BIT NOT NULL
    );

CREATE TABLE sec_role(
                         roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         roleName VARCHAR(30) NOT NULL UNIQUE
);
CREATE TABLE user_role
(
    id     BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL
);


ALTER TABLE user_role
    ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
        REFERENCES users (userId);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
        REFERENCES sec_role (roleId);