CREATE INDEX idx_author_name ON author (name);
CREATE INDEX idx_book_title ON book (title);
CREATE INDEX idx_author_book_author_id ON book_author (author_id);
CREATE INDEX idx_author_book_book_id ON book_author (book_id);