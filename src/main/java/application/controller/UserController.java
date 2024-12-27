package application.controller;

import application.DTO.UserDTO;
import application.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static application.utils.Constant.*;


/**
 * Controller class for handling User objects.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("rest/library/user")
public class UserController {

    /**
     * UserFacade interface
     *
     * @see UserFacade
     */
    private final UserFacade userFacade;

    /**
     * Endpoint to find user by ID.
     *
     * @param userId ID of the author.
     * @return ResponseEntity with success status and UserDTO object.
     * @see application.DTO.UserDTO
     */
    @GetMapping(path = "/get")
    public ResponseEntity<UserDTO> getAuthorById(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                                 @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                                  Long userId) {
        UserDTO userDTO = userFacade.findUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Endpoint to create a new user.
     *
     * @param userDTO the request containing user details.
     * @return ResponseEntity with success status.
     * @see application.DTO.UserDTO
     */
    @PostMapping(path = "/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        userFacade.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to update an existing user.
     *
     * @param userId  ID of the user that needs to be changed
     * @param userDTO the request containing new user details.
     * @return ResponseEntity with success status.
     * @see application.DTO.UserDTO
     */
    @PostMapping(path = "/update")
    public ResponseEntity<?> updateUser(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                        @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                        Long userId,
                                        @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userFacade.updateUser(userId, userDTO), HttpStatus.OK);
    }

    /**
     * Endpoint to delete user by ID.
     *
     * @param userId the ID of the user to be deleted.
     * @return ResponseEntity with success status.
     */
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam @NotNull(message = ID_CANNOT_BE_NULL)
                                        @Min(value = MIN_ID, message = ID_CANNOT_BE_LESS_1)
                                        Long userId) {
        userFacade.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
