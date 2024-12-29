package application.entity;

import application.entity.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static application.utils.Constant.*;

/**
 * Class Role represents a user Role entity with its attributes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum(USER, ADMIN)")
    @NotNull(message = ROLE_CANNOT_BE_NULL)
    private Roles role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
