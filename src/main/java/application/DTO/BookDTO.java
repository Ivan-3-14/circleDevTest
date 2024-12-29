package application.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer yearOfIssue;

    @ToString.Exclude
    private Set<AuthorDTO> authorDTOSet;
}
