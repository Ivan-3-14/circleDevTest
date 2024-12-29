package application.utils;

import application.entity.Author;
import application.entity.Book;
import application.entity.Role;
import application.entity.User;
import application.entity.enums.Roles;

import java.util.Set;

import static application.utils.MockConstant.*;

public class MockUtils {

    public static User getAdmin() {
        return User.builder()
                .name(ADMIN_NAME)
                .surname(ADMIN_NAME)
                .email(ADMIN_EMAIL)
                .password(ADMIN_PASSWORD)
                .roleSet(Set.of(new Role(1L, Roles.ADMIN)))
                .build();
    }

    public static User getUser() {
        return User.builder()
                .name(USER_NAME)
                .surname(USER_SURNAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .roleSet(Set.of(new Role(2L, Roles.USER)))
                .build();
    }

    public static User getNewUser() {
        return User.builder()
                .name(NEW_USER_NAME)
                .surname(NEW_USER_SURNAME)
                .email(NEW_USER_EMAIL)
                .password(NEW_USER_PASSWORD)
                .roleSet(Set.of(new Role(2L, Roles.USER)))
                .build();
    }

    public static Author getAuthor1() {
        return Author.builder()
                .name(AUTHOR_NAME1)
                .surname(AUTHOR_SURNAME1)
                .build();
    }

    public static Author getAuthor2() {
        return Author.builder()
                .name(AUTHOR_NAME2)
                .surname(AUTHOR_SURNAME2)
                .build();
    }

    public static Book getBook1() {
        return Book.builder()
                .name(BOOK_NAME1)
                .yearOfIssue(BOOK_YEAR_ISS1)
                .build();
    }

    public static Book getBook2() {
        return Book.builder()
                .name(BOOK_NAME2)
                .yearOfIssue(BOOK_YEAR_ISS2)
                .authorSet(Set.of(getAuthor2()))
                .build();
    }
}
