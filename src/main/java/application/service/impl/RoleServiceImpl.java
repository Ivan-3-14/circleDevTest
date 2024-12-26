package application.service.impl;

import application.entity.Role;
import application.entity.enums.Roles;
import application.repository.RoleRepository;
import application.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class implements the RoleService interface and provides methods for managing existing roles.
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * RoleRepository bean.
     *
     * @see application.repository.RoleRepository
     */
    final RoleRepository roleRepository;


    /**
     * Get a set of roles by role names.
     *
     * @param roleNames The set of role names.
     * @return The set of existing roles.
     */
    @Override
    public Set<Role> getRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(Roles::valueOf)
                .map(roleRepository::getByRole)
                .collect(Collectors.toSet());
    }
}