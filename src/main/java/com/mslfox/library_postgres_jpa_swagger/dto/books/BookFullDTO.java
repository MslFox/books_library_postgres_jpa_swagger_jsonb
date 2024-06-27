package com.mslfox.library_postgres_jpa_swagger.dto.books;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "BookFullInfo", description = "Includes full book's info")
public class BookFullDTO {
    private Long id;
    private String title;
    private String isbn;
    private List<Long> authorIds;
}