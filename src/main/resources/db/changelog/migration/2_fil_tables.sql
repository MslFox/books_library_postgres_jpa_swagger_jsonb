-- Вставка данных об авторах
INSERT INTO author (name, details)
VALUES ('Author1', '{"first-book": "Life is beautiful"}'),
       ('Author2', '{"age": 30}'),
       ('Author3', '{"birth-date": "10-10-99"}'),
       ('Author4', '{"alias": "Black", "city": "Moscow", "postCode": 646464 }');

INSERT INTO author (name)
VALUES ('Author5'),
       ('Author6'),
       ('Author7'),
       ('Author8'),
       ('Author9'),
       ('Author10');

-- Вставка данных о книгах
INSERT INTO book (title, isbn)
VALUES ('Book1', 'ISBN-1'),
       ('Book2', 'ISBN-2'),
       ('Book3', 'ISBN-3'),
       ('Book4', 'ISBN-4'),
       ('Book5', 'ISBN-5'),
       ('Book6', 'ISBN-6'),
       ('Book7', 'ISBN-7'),
       ('Book8', 'ISBN-8'),
       ('Book9', 'ISBN-9'),
       ('Book10', 'ISBN-10');

-- Вставка случайных связей между книгами и авторами
INSERT INTO book_author (book_id, author_id)
VALUES (1, 1),
       (1, 6),
       (1, 5),
       (2, 3),
       (2, 6),
       (2, 8),
       (3, 3),
       (3, 4),
       (3, 7),
       (4, 1),
       (4, 2);


