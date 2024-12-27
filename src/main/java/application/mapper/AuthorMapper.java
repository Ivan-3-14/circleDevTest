package application.mapper;

import application.DTO.AuthorDTO;
import application.DTO.BookDTO;
import application.entity.Author;
import application.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id")
    @Mapping(expression = "java(getBookSet(authorDTO))", target = "bookSet")
    Author toEntity(AuthorDTO authorDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getBookDTOSet(author))", target = "bookDTOSet")
    AuthorDTO toDTO(Author author);

    default Set<BookDTO> getBookDTOSet(Author author) {
        Set<BookDTO> bookDTOS = new HashSet<>();
        if (author != null && author.getBookSet() != null) {
            author.getBookSet().forEach(b -> bookDTOS.add(BookDTO.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .yearOfIssue(b.getYearOfIssue())
                    .build()));
        }
        return bookDTOS;
    }

    default Set<Book> getBookSet(AuthorDTO authorDTO) {
        Set<Book> books = new HashSet<>();
        if (authorDTO != null && authorDTO.getBookDTOSet() != null) {
            authorDTO.getBookDTOSet().forEach(b -> books.add(Book.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .yearOfIssue(b.getYearOfIssue())
                    .build()));
        }
        return books;
    }
}
