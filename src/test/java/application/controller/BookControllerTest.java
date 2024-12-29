package application.controller;

import application.entity.Book;
import application.utils.MockConstant;
import application.utils.MockUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static application.utils.MockConstant.*;

class BookControllerTest extends BaseTest {

    @Test
    void whenGetAllBooks_thenStatus200() {
        bookRepository.save(MockUtils.getBook1());
        bookRepository.save(MockUtils.getBook2());
        RestAssured.given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .get(GET_BOOKS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void whenGetBookById_thenStatus200() {
        Book book = MockUtils.getBook1();
        Long id = bookRepository.save(book).getId();
        checkStatusCodeInGetRequest(
                GET_BOOK + id,
                HttpStatus.OK.value()
        );
    }

    @Test
    void whenCreateBook_thanStatus201Test() {
        Book book = MockUtils.getBook1();
        checkStatusCodeInPostRequest(
                CREATE_BOOK,
                HttpStatus.CREATED.value(),
                book,
                ADMIN_CRED
        );
    }

    @Test
    void whenUpdateBook_thanStatus200Test() {
        Book book = MockUtils.getBook1();
        Book updateBook = bookRepository.save(book);
        updateBook.setName(MockUtils.getBook2().getName());
        checkStatusCodeAndBodyInPostRequest(
                UPDATE_BOOK,
                HttpStatus.OK.value(),
                SCHEME_SOURCE_PATH + BOOK_SCHEME,
                updateBook,
                ADMIN_CRED
        );
    }

    @Test
    void whenDeleteBook_thanStatus200Test() {
        Book book = MockUtils.getBook1();
        Long id = bookRepository.save(book).getId();
        checkStatusCodeInDeleteRequest(
                DELETE_BOOK,
                HttpStatus.OK.value(),
                BOOK_ID_PARAM,
                id,
                MockConstant.ADMIN_CRED
        );
    }
}