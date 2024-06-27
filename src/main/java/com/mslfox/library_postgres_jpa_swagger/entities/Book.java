package com.mslfox.library_postgres_jpa_swagger.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "book", indexes = @Index(name = "idx_book_title", columnList = "title"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            indexes = {
                    @Index(name = "idx_book_author_book_id", columnList = "book_id"),
                    @Index(name = "idx_book_author_author_id", columnList = "author_id")})
    private List<Author> authors;
}