package application.repository;

import application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface provides methods for accessing and manipulating Role entities in the database.
 *
 * @see application.entity.Role
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a Role entity by Roles (enum) value.
     *
     * @param role The role of the user associated with the role enum.
     * @return The user Role from database.
     */
    Role getByRole(String role);
}
