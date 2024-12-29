package application.controller;

import application.entity.Author;
import application.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static application.utils.MockConstant.*;

class AuthorControllerTest extends BaseTest {

    @Test
    void whenGetAuthorById_thenStatus200Test() {
        Author author = MockUtils.getAuthor1();
        Long id = authorRepository.save(author).getId();
        checkStatusCodeInGetRequest(
                GET_AUTHOR + id,
                HttpStatus.OK.value()
        );
    }

    @Test
    void whenCreateAuthor_thenStatus201Test() {
        Author author = MockUtils.getAuthor1();
        checkStatusCodeInPostRequest(
                CREATE_AUTHOR,
                HttpStatus.CREATED.value(),
                author,
                ADMIN_CRED
        );
    }

    @Test
    void whenUpdateAuthor_thenStatus200() {
        Author author = MockUtils.getAuthor1();
        Author updateAuthor = authorRepository.save(author);
        updateAuthor.setName(MockUtils.getAuthor2().getName());
        checkStatusCodeAndBodyInPostRequest(
                UPDATE_AUTHOR,
                HttpStatus.OK.value(),
                SCHEME_SOURCE_PATH + AUTHOR_SCHEME,
                updateAuthor,
                ADMIN_CRED
        );
    }

    @Test
    void whenDeleteAuthor_thenStatus200() {
        Author author = MockUtils.getAuthor1();
        Long id = authorRepository.save(author).getId();
        checkStatusCodeInDeleteRequest(
                DELETE_AUTHOR,
                HttpStatus.OK.value(),
                AUTHOR_ID_PARAM,
                id,
                ADMIN_CRED
        );
    }
}