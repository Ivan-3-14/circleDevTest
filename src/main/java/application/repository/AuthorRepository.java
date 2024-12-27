package application.repository;

import application.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface provides methods for accessing and manipulating Author entities in the database.
 *
 * @see application.entity.Author
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
