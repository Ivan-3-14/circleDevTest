package application.service.impl;

import application.entity.Author;
import application.repository.AuthorRepository;
import application.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AuthorService interface.
 *
 * @see application.service.AuthorService
 */
@Transactional
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    /**
     * AuthorRepository bean.
     *
     * @see application.repository.AuthorRepository
     */
    private final AuthorRepository authorRepository;

    /**
     * Create a new author.
     *
     * @param author the author object to save to the database.
     * @see application.entity.Author
     */
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Get the author with the given ID.
     *
     * @param authorId the ID of the author to get.
     * @return The Author object.
     * @see application.entity.Author
     */
    @Transactional(readOnly = true)
    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.getById(authorId);
    }

    /**
     * Updates an existing author.
     *
     * @param author the author object with date to update to the database.
     * @see application.entity.Author
     */
    @Override
    public Author updateAuthor(Author author) {
        return createAuthor(author);
    }

    /**
     * Delete the author with the given ID.
     *
     * @param authorId the ID of the author to delete.
     */
    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
