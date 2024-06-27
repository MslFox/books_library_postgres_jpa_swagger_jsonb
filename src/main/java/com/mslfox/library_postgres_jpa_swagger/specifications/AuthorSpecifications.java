package com.mslfox.library_postgres_jpa_swagger.specifications;

import com.mslfox.library_postgres_jpa_swagger.entities.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {
    public static Specification<Author> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name + "%");
    }

    public static Specification<Author> titleContains(String bookTitle) {
        return (root, query, criteriaBuilder) -> {
            root.join("books");
            return criteriaBuilder.like(root.get("books").get("title"), bookTitle + "%");
        };
    }

    public static Specification<Author> findByDetailsKey(String detailsKey) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.
                function("jsonb_exists", Boolean.class, root.get("details"), criteriaBuilder.literal(detailsKey)));
    }
}