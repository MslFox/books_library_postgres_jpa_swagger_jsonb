package com.mslfox.library_postgres_jpa_swagger.services;

import java.util.List;

public interface BookService<CreateDTO, UpdateDTO, FullDTO> {
    FullDTO save(CreateDTO dto);

    FullDTO update(Long id, UpdateDTO dto);

    void delete(Long id);

    List<FullDTO> findBooks(String param1, String param2, String param3);
}
