INSERT INTO users (email, encryptedPassword, enabled)
VALUES ('Jaskeerat@abc.com','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);


INSERT INTO watches (watchId, brand, price, stockQuantity, description, rating)
VALUES (1,'Rolex', 5000.00, 10, 'Luxury timepiece with stainless steel case', 4.8);


INSERT INTO watches (watchId, brand, price, stockQuantity, description, rating)
VALUES (2,'Seiko', 3000.50, 25, 'Affordable and reliable quartz watch', 4.2);


INSERT INTO watches (watchId, brand, price, stockQuantity, description, rating)
VALUES (3,'Omega', 7000.00, 5, 'High-end automatic chronograph watch', 4.9);


INSERT INTO watches (watchId, brand, price, stockQuantity, description, rating)
VALUES (4,'Casio', 5050.00, 50, 'Digital watch with multiple features', 3.5);


INSERT INTO watches (watchId, brand, price, stockQuantity, description, rating)
VALUES (5,'TAG Heuer', 6000.00, 8, 'Precision timekeeping with a sporty design', 4.7);


INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');

INSERT INTO user_role (userId, roleId)
VALUES (1, 3);
