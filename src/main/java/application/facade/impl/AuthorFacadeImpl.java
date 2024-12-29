package application.facade.impl;

import application.DTO.AuthorDTO;
import application.mapper.AuthorMapper;
import application.facade.AuthorFacade;
import application.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * This class implements the AuthorFacade interface and contains methods for creating, deleting, updating, and find authors.
 *
 * @see application.facade.AuthorFacade
 */
@RequiredArgsConstructor
@Component
public class AuthorFacadeImpl implements AuthorFacade {

    /**
     * AuthorMapper interface.
     *
     * @see application.mapper.AuthorMapper
     */
    private final AuthorMapper authorMapper;

    /**
     * AuthorService interface.
     *
     * @see application.service.AuthorService
     */
    private final AuthorService authorService;

    /**
     * Create a new author.
     *
     * @param authorDTO The AuthorDTO object containing information about the created author.
     * @see application.DTO.AuthorDTO
     */
    @Override
    public void createAuthor(@NotNull AuthorDTO authorDTO) {
        authorMapper.toDTO(authorService.createAuthor(authorMapper.toEntity(authorDTO)));
    }

    /**
     * Get current author by ID.
     *
     * @param authorId ID of the book.
     * @return AuthorDTO.
     * @see application.DTO.AuthorDTO
     */
    @Override
    public AuthorDTO findAuthorById(@NotNull Long authorId) {
        return authorMapper.toDTO(authorService.findAuthorById(authorId));
    }


    /**
     * Update author by ID with the given new data.
     *
     * @param authorDTO The AuthorDTO object containing information about the new date of author.
     * @return AuthorDTO.
     * @see application.DTO.AuthorDTO
     */
    @Override
    public AuthorDTO updateAuthor(@NotNull AuthorDTO authorDTO) {
        return authorMapper.toDTO(authorService.updateAuthor(authorMapper.toEntity(authorDTO)));
    }

    /**
     * Delete the author with the given ID.
     *
     * @param authorId the ID of the author to delete.
     */
    @Override
    public void deleteAuthor(@NotNull Long authorId) {
        Optional.ofNullable(authorId)
                .ifPresent(authorService::deleteAuthor);
    }
}
