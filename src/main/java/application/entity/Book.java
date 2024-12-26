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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    @Column(name = "year_of_issue", nullable = false)
    private Integer yearOfIssue;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "authors_id")})
    private Set<Author> authorSet = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "books_users",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<User> userSet = new HashSet<>();
}
