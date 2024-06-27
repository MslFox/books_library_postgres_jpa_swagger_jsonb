package com.mslfox.library_postgres_jpa_swagger.dto.authors;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "AuthorFullInfo", description = "Includes full author's info")
public class AuthorFullDTO {
    private Long id;
    private String name;
    private JsonNode details;
}
