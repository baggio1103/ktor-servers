CREATE TABLE starwarsfilms
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(50),
    sequel_id SMALLINT,
    director  VARCHAR(50)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50)
);

CREATE TABLE user_ratings(
    id SERIAL PRIMARY KEY ,
    value smallint,
    film_id INT,
    user_id INT,
    CONSTRAINT film_id_fk FOREIGN KEY(film_id) REFERENCES starwarsfilms(id),
    CONSTRAINT user_id_fk FOREIGN KEY(user_id) REFERENCES users(id)
)