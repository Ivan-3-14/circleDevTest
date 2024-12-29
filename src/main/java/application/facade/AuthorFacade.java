package application.facade;

import application.DTO.AuthorDTO;

import javax.validation.constraints.NotNull;

/**
 * This interface provides a simplified interface for managing authors.
 */
public interface AuthorFacade {

    /**
     * Create a new author.
     *
     * @param authorDTO The AuthorDTO object containing information about the created author.
     * @see application.DTO.AuthorDTO
     */
    void createAuthor(@NotNull AuthorDTO authorDTO);

    /**
     * Get current author by ID.
     *
     * @param authorId ID of the book.
     * @return AuthorDTO.
     * @see application.DTO.AuthorDTO
     */
    AuthorDTO findAuthorById(@NotNull Long authorId);

    /**
     * Update author by ID with the given new data.
     *
     * @param authorDTO The AuthorDTO object containing information about the new date of author.
     * @return AuthorDTO.
     * @see application.DTO.AuthorDTO
     */
    AuthorDTO updateAuthor(@NotNull AuthorDTO authorDTO);

    /**
     * Delete the author with the given ID.
     *
     * @param authorId the ID of the author to delete.
     */
    void deleteAuthor(@NotNull Long authorId);
}
