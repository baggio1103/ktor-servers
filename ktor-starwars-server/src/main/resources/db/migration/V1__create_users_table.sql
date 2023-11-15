CREATE TABLE users
(
    id   INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL UNIQUE,
    age  INTEGER NOT NULL
);

CREATE TABLE customers(
    id serial,
    name varchar,
    constraint pk_customer_id PRIMARY KEY (id)
)