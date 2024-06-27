package com.mslfox.library_postgres_jpa_swagger.services.impl;

import com.mslfox.library_postgres_jpa_swagger.advice.ResourceNotFoundException;
import com.mslfox.library_postgres_jpa_swagger.dto.books.BookCreateDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.books.BookFullDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.books.BookUpdateDTO;
import com.mslfox.library_postgres_jpa_swagger.entities.Author;
import com.mslfox.library_postgres_jpa_swagger.entities.Book;
import com.mslfox.library_postgres_jpa_swagger.mapper.BookMapper;
import com.mslfox.library_postgres_jpa_swagger.repositories.AuthorRepository;
import com.mslfox.library_postgres_jpa_swagger.repositories.BookRepository;
import com.mslfox.library_postgres_jpa_swagger.services.BookService;
import com.mslfox.library_postgres_jpa_swagger.specifications.BookSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService<BookCreateDTO, BookUpdateDTO, BookFullDTO> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookFullDTO save(BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setIsbn(bookCreateDTO.getIsbn());
        book.setAuthors(new ArrayList<>());
        bookCreateDTO.getAuthorIds().forEach(id -> {
            Author author = authorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Author's id: %d not found", id)));
            book.getAuthors().add(author);
        });
        return bookMapper.bookToBookFullDTO(bookRepository.save(book));
    }
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookFullDTO update(Long id, BookUpdateDTO bookUpdateDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(bookUpdateDTO.getTitle());

        if (StringUtils.hasText(bookUpdateDTO.getIsbn()))
            book.setIsbn(bookUpdateDTO.getIsbn());

        if (bookUpdateDTO.getAuthorIds() != null) {
            List<Author> authors = new ArrayList<>();
            bookUpdateDTO.getAuthorIds().forEach(authorId -> {
                Author author = authorRepository.findById(authorId)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format("Author's id: %d not found", authorId)));
                authors.add(author);
            });
            book.setAuthors(authors);
        }
        Book updatedBook = bookRepository.save(book);
        return bookMapper.bookToBookFullDTO(updatedBook);
    }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<BookFullDTO> findBooks(String title, String isbn, String authorName) {
        List<Specification<Book>> specifications = new ArrayList<>();
        if (StringUtils.hasText(title)) {
            specifications.add(BookSpecifications.titleContains(title));
        }
        if (StringUtils.hasText(isbn)) {
            specifications.add(BookSpecifications.isbnContains(isbn));
        }
        if (StringUtils.hasText(authorName)) {
            specifications.add(BookSpecifications.authorContains(authorName));
        }
        Specification<Book> finalSpecification = specifications.stream()
                .reduce(Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.and()),
                        Specification::and);
        specifications.forEach(System.out::println);
        List<Book> books = bookRepository.findAll(finalSpecification);
        return bookMapper.booksToBookFullDTOs(books);
    }

}