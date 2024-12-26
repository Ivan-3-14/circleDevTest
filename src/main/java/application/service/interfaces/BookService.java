package application.service.interfaces;


import application.entity.Book;

import java.util.List;

/**
 * The interface defines methods for managing books in the database.
 */
public interface BookService {

    /**
     * Creates a new book.
     *
     * @param book The Book object to save to the database.
     * @see application.entity.Book
     */
    Book createBook(Book book);

    /**
     * Retrieves a book by book`s id.
     *
     * @param bookId The ID of the book.
     */
    Book findBookById(Long bookId);

    /**
     * Updates an existing book.
     *
     * @param bookId The ID of the book to be edit in the database.
     * @param book Updated Book object.
     * @see application.entity.Book
     */
    Book updateBook(Long bookId, Book book);

    /**
     * Deletes the book with the specified ID.
     *
     * @param bookId The ID of the book to be deleted.
     */
    void deleteBookById(Long bookId);

    List<Book> getAllBooksByAuthor(Long authorId);

    List<Book> getAllBooks();

    boolean setAuthorToBook(Long bookId, Long authorId);
}
