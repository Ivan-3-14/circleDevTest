package application.repository;

import application.entity.Author;
import application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface provides methods for accessing and manipulating Book entities in the database.
 *
 * @see application.entity.Book
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Method find a list of all books by author
     *
     * @param author Author object that is contained in the set of authors of book
     * @return List<Book>
     */
    List<Book> findAllByAuthorSetContains(Author author);
}
