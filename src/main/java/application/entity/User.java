package application.entity;

import application.entity.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static application.utils.Constant.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = SURNAME_CANNOT_BE_EMPTY)
    private String surname;

    @Column(nullable = false, unique = true)
    @NotBlank(message = EMAIL_CANNOT_BE_NULL_OR_EMPTY)
    @Pattern(regexp = REGEXP_EMAIL, message = INVALID_EMAIL_MESSAGE)
    private String email;

    @Size(min = 4, message = PASSWORD_SIZE)
    @NotBlank(message = PASSWORD_CANNOT_BE_NULL_OR_EMPTY)
    private String password;

    @Transient
    private String passwordConfirm;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @NotEmpty(message = THE_ROLE_SET_CANNOT_BE_EMPTY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roleSet = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "userSet", fetch = FetchType.LAZY)
    private Set<Book> bookSet = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRoleAdmin() {
        return roleSet.stream()
                .anyMatch(role -> role.getRole() == Roles.ROLE_ADMIN);
    }
}
