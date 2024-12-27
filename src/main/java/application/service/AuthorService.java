package application.service;

import application.entity.Author;

/**
 * The interface provides methods to interact with author data.
 */
public interface AuthorService {

    /**
     * Create a new author.
     *
     * @param author the author object to save to the database.
     * @see application.entity.Author
     */
    Author createAuthor(Author author);

    /**
     * Get the author with the given ID.
     *
     * @param authorId the ID of the author to get.
     * @return The Author object.
     * @see application.entity.Author
     */
    Author findAuthorById(Long authorId);

    /**
     * Updates an existing author.
     *
     * @param author   the author object with date to update to the database.
     * @param authorId ID of the author that needs to be changed
     * @see application.entity.Author
     */
    Author updateAuthor(Long authorId, Author author);

    /**
     * Delete the author with the given ID.
     *
     * @param authorId the ID of the author to delete.
     */
    void deleteAuthor(Long authorId);
}
