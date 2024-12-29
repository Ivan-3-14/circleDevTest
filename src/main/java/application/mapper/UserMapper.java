package application.mapper;

import application.DTO.BookDTO;
import application.DTO.UserDTO;
import application.entity.Book;
import application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id")
    @Mapping(target = "roleSet")
    @Mapping(expression = "java(getBookSet(userDTO))", target = "bookSet")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id")
    @Mapping(target = "roleSet")
    @Mapping(expression = "java(getBookDTOSet(user))", target = "bookDTOSet")
    UserDTO toDTO(User user);

    default Set<BookDTO> getBookDTOSet(User user) {
        Set<BookDTO> bookDTOS = new HashSet<>();
        if (user != null && user.getBookSet() != null) {
            user.getBookSet().forEach(b -> bookDTOS.add(BookDTO.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .yearOfIssue(b.getYearOfIssue())
                    .build()));
        }
        return bookDTOS;
    }

    default Set<Book> getBookSet(UserDTO userDTO) {
        Set<Book> books = new HashSet<>();
        if (userDTO != null && userDTO.getBookDTOSet() != null) {
            userDTO.getBookDTOSet().forEach(b -> books.add(Book.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .yearOfIssue(b.getYearOfIssue())
                    .build()));
        }
        return books;
    }
}
