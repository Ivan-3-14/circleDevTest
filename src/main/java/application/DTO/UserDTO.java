package application.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

import static application.utils.Constant.NAME_CANNOT_BE_EMPTY;

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

    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
}