package com.mslfox.library_postgres_jpa_swagger.dto.authors;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "AuthorUpdate", description = "Used to update an author. All fields must be filled in")
public class AuthorUpdateDTO {
    @NotBlank
    private String name;
    @NotNull
    private JsonNode details;
}
