package application.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

import static application.utils.Constant.PASS_NOT_MATCH;
import static application.utils.Constant.USER_ALREADY_EXIST;

@ControllerAdvice
public class ExceptionApiHandler {

    /**
     * Handles exceptions related to the absence of the requested object in the system.
     *
     * @param exception The exception that was thrown.
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    /**
     * Handles exceptions that indicates that a method argument has not the expected type.
     *
     * @param exception The exception that was thrown.
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> mismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(exception.getMessage()));
    }

    /**
     * Handles exceptions that an object that does not exist or an entity that does not exist in the database
     *
     * @param exception The exception that was thrown.
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(exception.getMessage()));
    }

    /**
     * Handles exceptions that a method has been passed an illegal or inappropriate argument.
     *
     * @param exception The exception that was thrown.
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(exception.getMessage()));
    }

    /**
     * Handles exceptions associated with an attempt to add an object that already exists on the system
     *
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorMessage> userAlreadyExist() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(USER_ALREADY_EXIST));
    }

    /**
     * Handles exceptions related to mismatched passwords.
     *
     * @return ResponseEntity containing the exception response and ErrorMessage object with information message.
     */
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorMessage> passwordMismatchException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(PASS_NOT_MATCH));
    }
}
