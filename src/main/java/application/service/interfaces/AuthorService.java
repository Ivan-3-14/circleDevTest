package application.service.interfaces;

import application.entity.Author;


public interface AuthorService {

    Author createAuthor(Author authorDTO);

    Author findAuthorById(Long authorId);

    Author updateAuthor(Long authorId, Author authorDTO);

    void deleteAuthor(Long authorId);
}
