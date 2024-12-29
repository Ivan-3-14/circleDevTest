package application.facade;

import application.DTO.BookDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This interface provides a simplified interface for managing books.
 */
public interface BookFacade {

    /**
     * Create a new book.
     *
     * @param bookDTO The BookDTO object containing information about the created book.
     * @see application.DTO.BookDTO
     */
    void createBook(@NotNull BookDTO bookDTO);

    /**
     * Get current book by ID.
     *
     * @param bookId ID of the book.
     * @return BookDTO.
     * @see application.DTO.BookDTO
     */
    BookDTO findBookById(@NotNull Long bookId);

    /**
     * Update author by ID with the given new data.
     *
     * @param bookDTO The BookDTO object containing information about the new date of book.
     * @return BookDTO.
     * @see application.DTO.BookDTO
     */
    BookDTO updateBook(@NotNull BookDTO bookDTO);

    /**
     * Delete the book with the given ID.
     *
     * @param bookId the ID of the book to delete.
     */
    void deleteBookById(@NotNull Long bookId);

    /**
     * Get a list of all books by author ID.
     *
     * @param authorId the ID of the author whose books will be found
     * @return List<BookDTO>.
     */
    List<BookDTO> getAllBooksByAuthor(@NotNull Long authorId);

    /**
     * Get a list of all books in library.
     *
     * @return List<BookDTO>.
     */
    List<BookDTO> getAllBooks();

    /**
     * Set author's book.
     *
     * @param bookId   the ID of the book whose to be set author.
     * @param authorId the ID of the author which is added to the book.
     * @return boolean.
     */
    boolean setAuthorToBook(Long bookId, Long authorId);
}
