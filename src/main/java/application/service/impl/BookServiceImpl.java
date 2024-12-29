package application.service.impl;

import application.entity.Author;
import application.entity.Book;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of BookService interface.
 *
 * @see application.service.BookService
 */
@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    /**
     * BookRepository bean.
     *
     * @see application.repository.BookRepository
     */
    private final BookRepository bookRepository;

    /**
     * AuthorRepository bean.
     *
     * @see application.repository.AuthorRepository
     */
    private final AuthorRepository authorRepository;

    /**
     * Creates a new book.
     *
     * @param book The Book object to save to the database.
     * @see application.entity.Book
     */
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves a book by book`s id.
     *
     * @param bookId The ID of the book.
     */
    @Transactional(readOnly = true)
    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.getById(bookId);
    }

    /**
     * Updates an existing book.
     *
     * @param book Updated Book object.
     * @see application.entity.Book
     */
    @Override
    public Book updateBook(Book book) {
        Book temp = bookRepository.getById(book.getId());
        if (book.getAuthorSet() == null) {
            book.setAuthorSet(temp.getAuthorSet());
        } else {
            book.getAuthorSet().addAll(temp.getAuthorSet());
        }
        return createBook(book);
    }

    /**
     * Deletes the book with the specified ID.
     *
     * @param bookId The ID of the book to be deleted.
     */
    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    /**
     * Get a list of all books by author ID.
     *
     * @param authorId the ID of the author whose books will be found
     * @return List<BookDTO>.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthor(Long authorId) {
        Author author = authorRepository.getById(authorId);
        return new ArrayList<>(bookRepository.findAllByAuthorSetContains(author));
    }

    /**
     * Get a list of all books in library.
     *
     * @return List<BookDTO>.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    /**
     * Method to set author's book.
     *
     * @param bookId   the ID of the book whose to be set author
     * @param authorId the ID of the author which is added to the book
     * @return true if this book and this author are exists.
     */
    @Override
    public boolean setAuthorToBook(Long bookId, Long authorId) {
        try {
            Book book = bookRepository.getById(bookId);
            Author author = authorRepository.getById(authorId);

            if (book.getAuthorSet() != null) {
                book.getAuthorSet().add(author);
            } else {
                Set<Author> authors = new HashSet<>();
                authors.add(author);
                book.setAuthorSet(authors);
            }

            if (author.getBookSet() != null) {
                author.getBookSet().add(book);
            } else {
                Set<Book> books = new HashSet<>();
                books.add(book);
                author.setBookSet(books);
            }

            bookRepository.save(book);
            authorRepository.save(author);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }
}
