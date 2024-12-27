package application.exception;

/**
 * This exception is thrown when trying to create a user that already exists in the data system.
 */
public class UserAlreadyExist extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExist with a custom error message.
     *
     * @param message The custom error message to display.
     */
    public UserAlreadyExist(String message) {
        super(message);
    }

}
