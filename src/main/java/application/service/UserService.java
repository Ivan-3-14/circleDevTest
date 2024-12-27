package application.service;

import application.entity.User;

/**
 * The interface provides methods to interact with user data.
 */

public interface UserService {
    /**
     * Create a new user.
     *
     * @param user the user object to save to the database.
     * @see application.entity.User
     */
    User createUser(User user);

    /**
     * Get the user with the given ID.
     *
     * @param userId the ID of the author to get.
     * @return The User object.
     * @see application.entity.User
     */
    User findUserById(Long userId);

    /**
     * Updates an existing user.
     *
     * @param user   the user object with date to update to the database.
     * @param userId ID of the user that needs to be changed
     * @see application.entity.Author
     */
    User updateUser(Long userId, User user);

    /**
     * Delete the user with the given ID.
     *
     * @param userId the ID of the user to delete.
     */
    void deleteUser(Long userId);
}
