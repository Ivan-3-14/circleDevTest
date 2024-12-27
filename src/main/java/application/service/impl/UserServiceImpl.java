package application.service.impl;

import application.entity.User;
import application.entity.enums.Roles;
import application.exception.UserAlreadyExist;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static application.utils.Constant.USER_ALREADY_EXIST;

/**
 * Implementation of UserService interface.
 *
 * @see UserService
 */
@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * UserRepository bean.
     *
     * @see UserRepository
     */
    private final UserRepository userRepository;

    /**
     * RoleRepository bean.
     *
     * @see RoleRepository
     */
    private final RoleRepository roleRepository;

    /**
     * PasswordEncoder bean.
     *
     * @see application.config.SecurityConfig
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Create a new user and set role "user" and encoded password.
     *
     * @param user the user object to save to the database.
     * @return User object after saving to database
     * @see User
     */
    @Override
    public User createUser(User user) {
        checkEmail(user);
        user.getRoleSet().add(roleRepository.getByRole(Roles.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Get the user with the given ID.
     *
     * @param userId the ID of the user to get.
     * @return The User object.
     * @see User
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long userId) {
        return userRepository.getById(userId);
    }

    /**
     * Updates an existing user.
     *
     * @param user   the user object with date to update to the database.
     * @param userId ID of the user that needs to be changed
     * @see User
     */
    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        return createUser(user);
    }

    /**
     * Delete the User with the given ID.
     *
     * @param userId the ID of the user to delete.
     */
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Method to check if the user's email already exists in the database.
     *
     * @param user The user entity to check the email.
     * @throws UserAlreadyExist if the user's email already exists in the database.
     * @see User
     */
    private void checkEmail(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExist(USER_ALREADY_EXIST);
        }
    }
}
