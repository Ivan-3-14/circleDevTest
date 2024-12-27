package application.service;

import application.entity.User;

/**
 * Interface for authenticating and logging in users.
 */
public interface AuthorizationService {

    /**
     * Attempts to log in a user with the provided login information.
     *
     * @param userAuth the user login information.
     * @return a User object containing the details of the logged-in user.
     * @see application.entity.User
     */
    User loginUser(User userAuth);


}
