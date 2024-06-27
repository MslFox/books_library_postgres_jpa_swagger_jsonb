package com.mslfox.library_postgres_jpa_swagger.entities;


import com.fasterxml.jackson.databind.JsonNode;
import com.mslfox.library_postgres_jpa_swagger.converters.AuthorDetailsConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "author", indexes = @Index(name = "idx_author_name", columnList = "name"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = AuthorDetailsConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode details;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}