INSERT INTO starwarsfilms(sequel_id, name, director)
VALUES (1, 'Attack of Clones', 'Rian Johnson'),
       (6, 'The new hope', 'George Lukas'),
       (2, 'The rise of Skywalker', 'Dave Filoni');

INSERT INTO users(name, password)
VALUES ('baggio', '$2a$10$qV1a/8.MOcOmDITJlwjZDO74R2LxYxfYTsp6tsYdw4bQuT0MmHzUW'),
       ('dave', '$2a$10$qV1a/8.MOcOmDITJlwjZDOwZ3e0e55pwR.GloHMHM/AUU1stCGQSS'),
       ('bruce', '$2a$10$qV1a/8.MOcOmDITJlwjZDOv2IAOoNBNUDqjS/2clyDNpYWra8ByRa'),
       ('wayne', '$2a$10$qV1a/8.MOcOmDITJlwjZDO2V/jVXUJAIvEYxqZeohZQHrZTUJmvve'),
       ('jane', '$2a$10$qV1a/8.MOcOmDITJlwjZDOqEH0yj1GkpwGKUMw7K.sbxGOrnTfCHK'),
       ('david', '$2a$10$qV1a/8.MOcOmDITJlwjZDOyETIrRX0/qzVZrCpQ05fEqC4CEjt1Uq'),
       ('john', '$2a$10$qV1a/8.MOcOmDITJlwjZDOYuerSqmKeU1Xsc8Ec0Dv4mZxjxui/IO'),
       ('alice', '$2a$10$qV1a/8.MOcOmDITJlwjZDOYFPHRUnFU5AYxFYXl/xCzcl1/lXP1W.');

INSERT INTO user_ratings(value, film_id, user_id)
VALUES (5, 1, 1),
       (3, 2, 1),
       (5, 2, 2),
       (3, 3, 2),
       (4, 1, 3),
       (2, 2, 3),
       (1, 1, 4),
       (5, 3, 4);
