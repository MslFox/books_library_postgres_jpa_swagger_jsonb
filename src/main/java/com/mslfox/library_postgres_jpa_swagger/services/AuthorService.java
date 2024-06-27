package com.mslfox.library_postgres_jpa_swagger.services;

import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorFullDTO;

import java.util.List;

public interface AuthorService<CreateDTO, UpdateDTO, FullDTO> {
    FullDTO save(CreateDTO dto);

    FullDTO update(Long id, UpdateDTO dto);

    void delete(Long id);

    List<AuthorFullDTO> findAuthors(String param1, String param2, String param3);
}
