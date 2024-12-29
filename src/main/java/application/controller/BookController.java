package application.controller;

import application.DTO.BookDTO;
import application.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static application.utils.Constant.*;

/**
 * Controller class for handling Book objects.
 *
 * @see application.entity.Book
 */
@RestController
@RequestMapping("rest/library/book")
@RequiredArgsConstructor
public class BookController {

    /**
     * BookFacade interface
     *
     * @see BookFacade
     */
    private final BookFacade bookFacade;

    /**
     * Endpoint to get a list of all books.
     *
     * @return ResponseEntity with success status and List<BookDTO> objects or status "Not Found:.
     */
    @GetMapping(path = "/get/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOList = bookFacade.getAllBooks();

        return bookDTOList != null && !bookDTOList.isEmpty()
                ? new ResponseEntity<>(bookDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to find a book by ID.
     *
     * @param bookId ID of the book.
     * @return ResponseEntity with success status and BookDTO object.
     * @see application.DTO.BookDTO
     */
    @GetMapping(path = "/get")
    public ResponseEntity<BookDTO> getBookById(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                               @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                       Long bookId) {
        BookDTO bookDTO = bookFacade.findBookById(bookId);
        return ResponseEntity.ok(bookDTO);
    }

    /**
     * Endpoint to create a new book.
     *
     * @param bookDTO the request containing book details.
     * @return ResponseEntity with success status.
     * @see application.DTO.BookDTO
     */
    @PostMapping(path = "/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookDTO bookDTO) {
        bookFacade.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to update an existing book.
     *
     * @param newBookDTO the request containing book details.
     * @return ResponseEntity with success status.
     * @see application.DTO.BookDTO
     */
    @PostMapping(path = "/admin/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookDTO newBookDTO) {
        return new ResponseEntity<>(bookFacade.updateBook(newBookDTO), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a book by ID.
     *
     * @param bookId the ID of the book to be deleted.
     * @return ResponseEntity with success status.
     */
    @DeleteMapping(path = "/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                        @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                Long bookId) {
        bookFacade.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to get a list of all books by author ID.
     *
     * @param authorId the ID of the author whose books will be found
     * @return ResponseEntity with success status and List<BookDTO> objects or status "Not Found:.
     */
    @GetMapping(path = "/get/byAuthor")
    public ResponseEntity<List<BookDTO>> getBooksByAuthorId(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                                            @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                                    Long authorId) {
        List<BookDTO> bookDTOListByAuthorId = bookFacade.getAllBooksByAuthor(authorId);

        return bookDTOListByAuthorId != null && !bookDTOListByAuthorId.isEmpty()
                ? new ResponseEntity<>(bookDTOListByAuthorId, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to set author's book.
     *
     * @param bookId   the ID of the book whose to be set author
     * @param authorId the ID of the author which is added to the book
     * @return ResponseEntity with success status and List<BookDTO> objects or status "Not Found:.
     */
    @PostMapping(path = "/admin/author/set")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> setAuthorToBook(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                             @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                     Long bookId,
                                             @RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                             @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                     Long authorId) {

        return bookFacade.setAuthorToBook(bookId, authorId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
