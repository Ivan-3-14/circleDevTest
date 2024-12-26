package application.service.interfaces;

import application.entity.Role;

import java.util.Set;

/**
 * The interface provides methods to interact with existing roles.
 */

public interface RoleService {

    /**
     * Get a set of roles by role names.
     * @param roleNames The set of role names.
     * @return The set of existing roles.
     */
    Set<Role> getRoles(Set<String> roleNames);
}
