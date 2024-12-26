package application.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import static application.utils.Constant.NAME_CANNOT_BE_EMPTY;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    @Column(name = "surname")
    private String surname;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authorSet", fetch = FetchType.LAZY)
    private Set<Book> bookSet = new HashSet<>();
}
