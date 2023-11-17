INSERT INTO starwarsfilms(sequel_id, name, director)
VALUES (1, 'Attack of Clones', 'Rian Johnson'),
       (6, 'The new hope', 'George Lukas'),
       (2, 'The rise of Skywalker', 'Dave Filoni');

INSERT INTO users(name)
VALUES ('baggio'),
       ('dave'),
       ('bruce'),
       ('wayne'),
       ('jane'),
       ('david'),
       ('john'),
       ('alice');

INSERT INTO user_ratings(value, film_id, user_id)
VALUES (5, 1, 1),
       (3, 2, 1),
       (5, 2, 2),
       (3, 3, 2),
       (4, 1, 3),
       (2, 2, 3),
       (1, 1, 4),
       (5, 3, 4);
