package application.controller;

import application.DTO.AuthorDTO;
import application.facade.AuthorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static application.utils.Constant.*;

/**
 * Controller class for handling Author objects.
 *
 * @see application.entity.Author
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("rest/library/author")
public class AuthorController {

    /**
     * AuthorFacade interface
     *
     * @see AuthorFacade
     */
    private final AuthorFacade authorFacade;

    /**
     * Endpoint to find author by ID.
     *
     * @param authorId ID of the author.
     * @return ResponseEntity with success status and AuthorDTO object.
     * @see application.DTO.AuthorDTO
     */
    @GetMapping(path = "/get")
    public ResponseEntity<AuthorDTO> getAuthorById(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                                   @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                           Long authorId) {
        AuthorDTO authorDTO = authorFacade.findAuthorById(authorId);
        return ResponseEntity.ok(authorDTO);
    }

    /**
     * Endpoint to create a new author.
     *
     * @param authorDTO the request containing author details.
     * @return ResponseEntity with success status.
     * @see application.DTO.AuthorDTO
     */
    @PostMapping(path = "/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        authorFacade.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to update an existing author.
     *
     * @param newAuthorDTO the request containing new author details.
     * @return ResponseEntity with success status.
     * @see application.DTO.AuthorDTO
     */
    @PostMapping(path = "/admin/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorDTO newAuthorDTO) {
        return new ResponseEntity<>(authorFacade.updateAuthor(newAuthorDTO), HttpStatus.OK);
    }

    /**
     * Endpoint to delete author by ID.
     *
     * @param authorId the ID of the author to be deleted.
     * @return ResponseEntity with success status.
     */
    @DeleteMapping(path = "/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAuthor(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                          @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                  Long authorId) {
        authorFacade.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
