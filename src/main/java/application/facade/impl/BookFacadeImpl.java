package application.facade.impl;

import application.DTO.BookDTO;
import application.conventer.BookMapper;
import application.facade.interfaces.BookFacade;
import application.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the BookFacade interface. Contains methods for creating, deleting, updating and find books and also
 * method for find all books, find books ba ID of author and method to set author's book
 *
 * @see application.facade.interfaces.BookFacade
 */
@RequiredArgsConstructor
@Component
public class BookFacadeImpl implements BookFacade {

    /**
     * BookService interface
     *
     * @see application.service.interfaces.BookService
     */
    private final BookService bookService;

    /**
     * BookMapper interface
     *
     * @see application.conventer.BookMapper
     */
    private final BookMapper bookMapper;

    /**
     * Create a new book.
     *
     * @param bookDTO The BookDTO object containing information about the created book.
     * @return BookDTO.
     */
    @Override
    public BookDTO createBook(@NotNull BookDTO bookDTO) {
        return bookMapper.toDTO(bookService.createBook(bookMapper.toEntity(bookDTO)));
    }

    /**
     * Get current book by ID.
     *
     * @param bookId ID of the book.
     * @return BookDTO.
     */
    @Override
    public BookDTO findBookById(@NotNull Long bookId) {
        return bookMapper.toDTO(bookService.findBookById(bookId));
    }

    /**
     * Update author by ID with the given new data.
     *
     * @param bookDTO The BookDTO object containing information about the new date of book.
     * @param bookId ID of the book that needs to be changed
     * @return BookDTO.
     */
    @Override
    public BookDTO updateBook(@NotNull Long bookId, @NotNull BookDTO bookDTO) {
        return bookMapper.toDTO(bookService.updateBook(bookId, bookMapper.toEntity(bookDTO)));
    }

    /**
     * Delete the book with the given ID.
     *
     * @param bookId the ID of the book to delete.
     */
    @Override
    public void deleteBookById(@NotNull Long bookId) {
        bookService.deleteBookById(bookId);
    }

    /**
     * Get a list of all books by author ID.
     *
     * @param authorId the ID of the author whose books will be found
     * @return List<BookDTO>.
     */
    @Override
    public List<BookDTO> getAllBooksByAuthor(@NotNull Long authorId) {
        List<BookDTO> result = new ArrayList<>();
        bookService.getAllBooksByAuthor(authorId)
                .forEach(b -> result.add(bookMapper.toDTO(b)));
        return result;
    }

    /**
     * Get a list of all books.
     *
     * @return List<BookDTO>.
     */
    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> result = new ArrayList<>();
        bookService.getAllBooks()
                .forEach(b -> result.add(bookMapper.toDTO(b)));
        return result;
    }

    /**
     * Set author's book.
     *
     * @param bookId the ID of the book whose to be set author
     * @param authorId the ID of the author which is added to the book
     * @return boolean.
     */
    @Override
    public boolean setAuthorToBook(@NotNull Long bookId, @NotNull Long authorId) {
       return bookService.setAuthorToBook(bookId, authorId);
    }
}
