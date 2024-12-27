package application.service;


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
     * @param book   Updated Book object.
     * @see application.entity.Book
     */
    Book updateBook(Long bookId, Book book);

    /**
     * Deletes the book with the specified ID.
     *
     * @param bookId The ID of the book to be deleted.
     */
    void deleteBookById(Long bookId);

    /**
     * Get a list of all books by author ID.
     *
     * @param authorId the ID of the author whose books will be found
     * @return List<BookDTO>.
     */
    List<Book> getAllBooksByAuthor(Long authorId);

    /**
     * Get a list of all books in library.
     *
     * @return List<BookDTO>.
     */
    List<Book> getAllBooks();

    /**
     * Method to set author's book.
     *
     * @param bookId   the ID of the book whose to be set author
     * @param authorId the ID of the author which is added to the book
     * @return true if this book and this author are exists.
     */
    boolean setAuthorToBook(Long bookId, Long authorId);
}
