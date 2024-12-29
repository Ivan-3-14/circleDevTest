package application.facade.impl;

import application.DTO.BookDTO;
import application.mapper.BookMapper;
import application.facade.BookFacade;
import application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the BookFacade interface. Contains methods for creating, deleting, updating and find books and also
 * method for find all books, find books ba ID of author and method to set author's book
 *
 * @see BookFacade
 */
@RequiredArgsConstructor
@Component
public class BookFacadeImpl implements BookFacade {

    /**
     * BookService interface
     *
     * @see BookService
     */
    private final BookService bookService;

    /**
     * BookMapper interface
     *
     * @see application.mapper.BookMapper
     */
    private final BookMapper bookMapper;

    /**
     * Create a new book.
     *
     * @param bookDTO The BookDTO object containing information about the created book.
     */
    @Override
    public void createBook(@NotNull BookDTO bookDTO) {
        bookMapper.toDTO(bookService.createBook(bookMapper.toEntity(bookDTO)));
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
     * @return BookDTO.
     */
    @Override
    public BookDTO updateBook(@NotNull BookDTO bookDTO) {
        return bookMapper.toDTO(bookService.updateBook(bookMapper.toEntity(bookDTO)));
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
        return bookService.getAllBooksByAuthor(authorId).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a list of all books.
     *
     * @return List<BookDTO>.
     */
    @Override
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Set author's book.
     *
     * @param bookId   the ID of the book whose to be set author.
     * @param authorId the ID of the author which is added to the book.
     * @return true if this book and this author are exists.
     */
    @Override
    public boolean setAuthorToBook(@NotNull Long bookId, @NotNull Long authorId) {
        return bookService.setAuthorToBook(bookId, authorId);
    }
}
