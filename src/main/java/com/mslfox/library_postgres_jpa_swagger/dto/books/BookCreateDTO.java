package com.mslfox.library_postgres_jpa_swagger.dto.books;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "BookCreate", description = "Used to create the book. 'title' and 'authorIds' field must be filled in")
public class BookCreateDTO {
    @NotBlank
    private String title;
    private String isbn;
    @NotNull
    @NotEmpty
    private List<Long> authorIds;
}
