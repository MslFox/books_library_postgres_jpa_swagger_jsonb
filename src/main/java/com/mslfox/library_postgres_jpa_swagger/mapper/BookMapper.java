package com.mslfox.library_postgres_jpa_swagger.mapper;

import com.mslfox.library_postgres_jpa_swagger.dto.books.BookFullDTO;
import com.mslfox.library_postgres_jpa_swagger.entities.Author;
import com.mslfox.library_postgres_jpa_swagger.entities.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorIds", ignore = true)
    BookFullDTO bookToBookFullDTO(Book book);

    @AfterMapping
    default void mapAuthorsToAuthorIds(Book book, @MappingTarget BookFullDTO bookFullDTO) {
        bookFullDTO.setAuthorIds(book.getAuthors().stream().map(Author::getId).toList());
    }

    List<BookFullDTO> booksToBookFullDTOs(List<Book> books);
}
