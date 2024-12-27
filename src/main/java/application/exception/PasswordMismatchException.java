package application.exception;

/**
 * This exception is thrown if the two passwords being checked do not match.
 */

public class PasswordMismatchException extends RuntimeException {

    /**
     * Constructs a new PasswordMismatchException with a custom error message.
     *
     * @param message The custom error message to display.
     */
    public PasswordMismatchException(String message) {
        super(message);
    }
}
