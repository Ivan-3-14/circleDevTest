package application.DTO;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 4, max = 4)
    private Integer yearOfIssue;

    @ToString.Exclude
    private Set<AuthorDTO> authorDTOSet;
}
