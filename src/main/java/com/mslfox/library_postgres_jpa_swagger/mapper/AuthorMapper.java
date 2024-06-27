package com.mslfox.library_postgres_jpa_swagger.mapper;

import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorFullDTO;
import com.mslfox.library_postgres_jpa_swagger.entities.Author;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorFullDTO authorToAuthorFullDTO(Author author) ;
    List<AuthorFullDTO> authorsToAuthorFullDTOs(List<Author> authors);
}