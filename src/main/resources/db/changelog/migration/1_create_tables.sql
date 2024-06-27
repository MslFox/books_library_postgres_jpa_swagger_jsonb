-- Создание таблицы Author
CREATE TABLE author
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    details JSONB
);

-- Создание таблицы Book
CREATE TABLE book
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn  VARCHAR(255)
);

-- Создание таблицы для связи many-to-many
CREATE TABLE book_author
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);