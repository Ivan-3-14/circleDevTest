package application.DTO;

import application.entity.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

import static application.utils.Constant.*;
import static application.utils.Constant.INVALID_EMAIL_MESSAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    private String surname;

    @ToString.Exclude
    private Set<BookDTO> bookDTOSet;

    private Set<Role> roleSet;

    @NotBlank(message = EMAIL_CANNOT_BE_NULL_OR_EMPTY)
    @Pattern(regexp = REGEXP_EMAIL, message = INVALID_EMAIL_MESSAGE)
    private String email;

    @Size(min = 4, message = PASSWORD_SIZE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
}
