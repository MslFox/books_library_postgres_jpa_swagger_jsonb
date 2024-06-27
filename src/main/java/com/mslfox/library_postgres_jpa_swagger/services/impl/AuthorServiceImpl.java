package com.mslfox.library_postgres_jpa_swagger.services.impl;

import com.mslfox.library_postgres_jpa_swagger.advice.ResourceNotFoundException;
import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorCreateDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorFullDTO;
import com.mslfox.library_postgres_jpa_swagger.dto.authors.AuthorUpdateDTO;
import com.mslfox.library_postgres_jpa_swagger.entities.Author;
import com.mslfox.library_postgres_jpa_swagger.mapper.AuthorMapper;
import com.mslfox.library_postgres_jpa_swagger.repositories.AuthorRepository;
import com.mslfox.library_postgres_jpa_swagger.services.AuthorService;
import com.mslfox.library_postgres_jpa_swagger.specifications.AuthorSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService<AuthorCreateDTO, AuthorUpdateDTO, AuthorFullDTO> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public AuthorFullDTO save(AuthorCreateDTO authorCreateDTO) {
        Author author = new Author();
        author.setName(authorCreateDTO.getName());
        if (authorCreateDTO.getDetails() != null && !authorCreateDTO.getDetails().isEmpty())
            author.setDetails(authorCreateDTO.getDetails());
        return authorMapper.authorToAuthorFullDTO(authorRepository.save(author));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AuthorFullDTO update(Long id, AuthorUpdateDTO authorUpdateDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Author not found with id: %d", id)));
        author.setName(authorUpdateDTO.getName());
        author.setDetails(authorUpdateDTO.getDetails());
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorFullDTO(updatedAuthor);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<AuthorFullDTO> findAuthors(String name, String bookTitle, String detailsKey) {
        Specification<Author> finalSpecification = Specification.where(null);
        if (StringUtils.hasText(name))
            finalSpecification.and(AuthorSpecifications.nameContains(name));
        if (StringUtils.hasText(bookTitle))
            finalSpecification.and(AuthorSpecifications.titleContains(bookTitle));
        if (StringUtils.hasText(detailsKey))
            finalSpecification.and(AuthorSpecifications.findByDetailsKey(detailsKey));
        List<Author> authors = authorRepository.findAll(finalSpecification);
        return authorMapper.authorsToAuthorFullDTOs(authors);
    }
}