package application.facade;

import application.DTO.UserDTO;

import javax.validation.constraints.NotNull;

/**
 * This interface provides a simplified interface for managing users.
 */
public interface UserFacade {

    /**
     * Create a new User.
     *
     * @param userDTO object containing information about the created user.
     * @see application.DTO.UserDTO
     */
    void createUser(@NotNull UserDTO userDTO);

    /**
     * Get current author by ID.
     *
     * @param userId ID of the book.
     * @return UserDTO.
     * @see application.DTO.UserDTO
     */
    UserDTO findUserById(@NotNull Long userId);

    /**
     * Update author by ID with the given new data.
     *
     * @param userDTO The UserDTO object containing information about the new date of user.
     * @return UserDTO.
     * @see application.DTO.UserDTO
     */
    UserDTO updateUser(@NotNull UserDTO userDTO);

    /**
     * Delete the author with the given ID.
     *
     * @param userId the ID of the user to delete.
     */
    void deleteUser(@NotNull Long userId);
}
