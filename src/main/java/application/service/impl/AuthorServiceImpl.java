package application.service.impl;

import application.entity.Author;
import application.repository.AuthorRepository;
import application.service.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.getById(authorId);
    }

    @Override
    public Author updateAuthor(Long authorId, Author author) {
        author.setId(authorId);
        return createAuthor(author);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
