package application.repository;

import application.entity.User;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface provides methods for accessing and manipulating User entities in the database.
 *
 * @see application.entity.User
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return An Optional containing the user with the specified email, if found.
     */
    Optional<User> getByEmail(String email);

    /**
     * Checks if a user exists with the given email address.
     *
     * @param email The email address to check.
     * @return true if a user with the email address exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete. Must not be {@literal null}.
     */
    void deleteById(@Nullable Long id);
}
