package com.mslfox.library_postgres_jpa_swagger.dto.authors;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "AuthorCreate", description = "Used to create an author. 'name' field field must be filled in")
public class AuthorCreateDTO {
    @NotBlank
    private String name;
    private JsonNode details;

}
