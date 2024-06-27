package com.mslfox.library_postgres_jpa_swagger.controllers;

import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorCreateDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorFullDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorUpdateDTO;
import com.mslfox.library_postgres_jpa_swagger.services.impl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @PostMapping
    @Operation(description = "Create new author")
    public ResponseEntity<AuthorFullDTO> addAuthor(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        System.out.println(authorCreateDTO);
        return new ResponseEntity<>(authorService.save(authorCreateDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(description = "Updated existed author")
    public ResponseEntity<AuthorFullDTO> updateAuthor(@PathVariable @Min(value = 1) Long id, @RequestBody @Valid AuthorUpdateDTO authorUpdateDTO) {
        return ResponseEntity.ok(authorService.update(id, authorUpdateDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete existed author")
    public ResponseEntity<Void> deleteAuthor(@PathVariable @Min(value = 1) Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(description = "Full list of authors will be returned with no parameters")
    public ResponseEntity<List<AuthorFullDTO>> getAuthors(
            @Parameter(description = "Search authors by name")
            @RequestParam(required = false) String name,
            @Parameter(description = "Search authors by book bookTitle")
            @RequestParam(required = false, name = "book-title") String bookTitle,
            @Parameter(description = "Search authors by details key name ")
            @RequestParam(required = false, name = "details-key") String detailsFieldName) {
        return ResponseEntity.ok(authorService.findAuthors(name, bookTitle, detailsFieldName));
    }
}
