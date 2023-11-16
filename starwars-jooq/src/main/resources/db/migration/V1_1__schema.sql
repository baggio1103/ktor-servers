CREATE TABLE starwarsfilms
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(50),
    sequel_id SMALLINT,
    director  VARCHAR(50)
);