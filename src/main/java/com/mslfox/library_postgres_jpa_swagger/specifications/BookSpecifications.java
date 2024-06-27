package com.mslfox.library_postgres_jpa_swagger.specifications;

import com.mslfox.library_postgres_jpa_swagger.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> titleContains(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"),  title + "%");
    }

    public static Specification<Book> isbnContains(String isbn) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("isbn"),  isbn + "%");
    }
    public static Specification<Book> authorContains(String authorName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.join("authors").get("name"), authorName + "%");
    }
}