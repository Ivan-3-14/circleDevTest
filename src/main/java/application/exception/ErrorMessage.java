package application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error message class. Reports information about the caught exception
 */
@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorMessage {
    /**
     * Text message sent to the user when an exception occurs
     */
    private final String message;

    /**
     * Constructs a new ErrorMessage with a custom error message.
     */
    public ErrorMessage(String message) {
        this.message = message;
    }

}
