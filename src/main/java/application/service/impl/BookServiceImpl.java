package application.service.impl;

import application.entity.Author;
import application.entity.Book;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.getById(bookId);
    }

    @Override
    public Book updateBook(Long bookId, Book book) {
        Book temp = bookRepository.getById(bookId);
        book.setId(bookId);
        if (book.getAuthorSet() == null) {
            book.setAuthorSet(temp.getAuthorSet());
        } else {
            book.getAuthorSet().addAll(temp.getAuthorSet());
        }
        return createBook(book);
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthor(Long authorId) {
        Author author = authorRepository.getById(authorId);
        return new ArrayList<>(bookRepository.findAllByAuthorSetContains(author));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    @Override
    public boolean setAuthorToBook(Long bookId, Long authorId) {
        try {
            Book book = bookRepository.getById(bookId);
            Author author = authorRepository.getById(authorId);

            if (book.getAuthorSet() != null) {
                book.getAuthorSet().add(author);
            } else {
                Set<Author> authors = new HashSet<>();
                authors.add(author);
                book.setAuthorSet(authors);
            }

            if (author.getBookSet() != null) {
                author.getBookSet().add(book);
            } else {
                Set<Book> books = new HashSet<>();
                books.add(book);
                author.setBookSet(books);
            }

            bookRepository.save(book);
            authorRepository.save(author);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
