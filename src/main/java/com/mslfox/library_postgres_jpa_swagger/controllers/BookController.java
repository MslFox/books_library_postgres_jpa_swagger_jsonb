package com.mslfox.library_postgres_jpa_swagger.controllers;

import com.mslfox.library_postgres_jpa_swagger.dto.books.BookCreateDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.books.BookFullDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.books.BookUpdateDTO;
import com.mslfox.library_postgres_jpa_swagger.services.impl.BookServiceImpl;
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
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @Operation(description = "Create new book")
    @PostMapping
    public ResponseEntity<BookFullDTO> addBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        return new ResponseEntity<>(bookService.save(bookCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update existed book")
    public ResponseEntity<BookFullDTO> updateBook(@PathVariable @Min(value = 1) Long id, @RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        return ResponseEntity.ok(bookService.update(id, bookUpdateDTO));
    }

    @Operation(description = "Delete existed book")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable @Min(value = 1) Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Full list of books will be returned with no parameter")
    @GetMapping
    public ResponseEntity<List<BookFullDTO>> getBooks(
            @Parameter(description = "Search books by title")
            @RequestParam(required = false) String title,
            @Parameter(description = "Search books by ISBN")
            @RequestParam(required = false) String isbn,
            @Parameter(description = "Search books by author's name")
            @RequestParam(required = false, name = "author") String authorName) {
        return ResponseEntity.ok(bookService.findBooks(title, isbn, authorName));
    }
}


