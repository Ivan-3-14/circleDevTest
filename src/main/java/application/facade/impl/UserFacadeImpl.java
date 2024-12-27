package application.facade.impl;

import application.DTO.UserDTO;
import application.facade.UserFacade;
import application.mapper.UserMapper;
import application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * This class implements the UserFacade interface and contains methods for creating, deleting, updating, and find users.
 *
 * @see UserFacade
 */
@RequiredArgsConstructor
@Component
public class UserFacadeImpl implements UserFacade {

    /**
     * UserMapper interface.
     *
     * @see UserMapper
     */
    private final UserMapper userMapper;

    /**
     * UserService interface.
     *
     * @see UserService
     */
    private final UserService userService;

    /**
     * Create a new user.
     *
     * @param userDTO The UserDTO object containing information about the created user.
     * @see UserDTO
     */
    @Override
    public void createUser(@NotNull UserDTO userDTO) {
        userMapper.toDTO(userService.createUser(userMapper.toEntity(userDTO)));
    }

    /**
     * Get current user by ID.
     *
     * @param userId ID of the book.
     * @return UserDTO.
     * @see UserDTO
     */
    @Override
    public UserDTO findUserById(@NotNull Long userId) {
        return userMapper.toDTO(userService.findUserById(userId));
    }


    /**
     * Update user by ID with the given new data.
     *
     * @param userDTO The UserDTO object containing information about the new date of user.
     * @param userId  ID of the user that needs to be changed
     * @return UserDTO.
     * @see UserDTO
     */
    @Override
    public UserDTO updateUser(@NotNull Long userId, @NotNull UserDTO userDTO) {
        return userMapper.toDTO(userService.updateUser(userId, userMapper.toEntity(userDTO)));
    }

    /**
     * Delete the user with the given ID.
     *
     * @param userId the ID of the user to delete.
     */
    @Override
    public void deleteUser(@NotNull Long userId) {
        Optional.ofNullable(userId)
                .ifPresent(userService::deleteUser);

    }
}
