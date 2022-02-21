CREATE TABLE if NOT EXISTS product
(
    id integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(200),
    price decimal,
    quantity integer
);