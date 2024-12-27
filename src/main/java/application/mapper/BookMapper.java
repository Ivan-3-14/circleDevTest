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
public interface BookMapper {

    @Mapping(target = "id")
    @Mapping(expression = "java(getAuthorSet(bookDTO))", target = "authorSet")
    Book toEntity(BookDTO bookDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getAuthorDTOSet(book))", target = "authorDTOSet")
    BookDTO toDTO(Book book);

    default Set<AuthorDTO> getAuthorDTOSet(Book book) {
        Set<AuthorDTO> authorDTOS = new HashSet<>();
        if (book != null && book.getAuthorSet() != null) {
            book.getAuthorSet().forEach(a -> authorDTOS.add(AuthorDTO.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .surname(a.getSurname())
                    .build()));
        }
        return authorDTOS;
    }

    default Set<Author> getAuthorSet(BookDTO bookDTO) {
        Set<Author> authors = new HashSet<>();
        if (bookDTO != null && bookDTO.getAuthorDTOSet() != null) {
            bookDTO.getAuthorDTOSet().forEach(a -> authors.add(Author.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .surname(a.getSurname())
                    .build()));
        }
        return authors;
    }
}
