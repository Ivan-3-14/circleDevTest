package application.controller;

import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.repository.UserRepository;
import application.utils.MockConstant;
import application.utils.MockUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static application.utils.MockConstant.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected BookRepository bookRepository;

    @BeforeAll
    static void setUp() {
        RestAssured.registerParser("text/html;charset=UTF-8", Parser.JSON);
        RestAssured.given()
                .baseUri(MockConstant.BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    @BeforeEach  public void insertData() {
        userRepository.deleteAll();
        userRepository.save(MockUtils.getAdmin());
        userRepository.save(MockUtils.getUser());
    }


    @AfterEach
    public void deleteData() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        userRepository.deleteAll();
    }

    protected void checkStatusCodeInGetRequest(String url, int code) {
        String accessToken = getAccessToken(MockConstant.USER_CRED);
        RestAssured.given()
                .log().all()
                .header(TOKEN_HEADER, TOKEN_TYPE + accessToken)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(code)
                .time(lessThan(MockConstant.DEFAULT_TIMEOUT));
    }

    protected void checkStatusCodeInDeleteRequest(String url, int code, String param,
                                                  Long id, String validUserDataJson) {
        String accessToken = getAccessToken(validUserDataJson);
        RestAssured.given()
                .log().all()
                .header(TOKEN_HEADER, TOKEN_TYPE + accessToken)
                .param(param, id)
                .when()
                .delete(url)
                .then()
                .log().all()
                .statusCode(code)
                .time(lessThan(MockConstant.DEFAULT_TIMEOUT));
    }

    protected void checkStatusCodeAndBodyInGetRequest(String url, int code, String schema,
                                                      String validUserDataJson) {
        String accessToken = getAccessToken(validUserDataJson);
        RestAssured.given()
                .log().all()
                .header(TOKEN_HEADER, TOKEN_TYPE + accessToken)
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(code)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(schema))
                .time(lessThan(MockConstant.DEFAULT_TIMEOUT));
    }

    protected void checkStatusCodeAndBodyInPostRequest(String url, int code, String schema,
                                                       Object requestBody, String validUserDataJson) {
        String accessToken = getAccessToken(validUserDataJson);
        RestAssured.given()
                .log().all()
                .header(TOKEN_HEADER, TOKEN_TYPE + accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .port(MockConstant.DEFAULT_APP_PORT)
                .post(url)
                .then()
                .log().all()
                .statusCode(code)
                .body(matchesJsonSchemaInClasspath(schema))
                .time(lessThan(MockConstant.DEFAULT_TIMEOUT));
    }

    protected void checkStatusCodeInPostRequest(String url, int code,
                                                Object requestBody, String validUserDataJson) {
        String accessToken = getAccessToken(validUserDataJson);
        RestAssured.given()
                .log().all()
                .header(TOKEN_HEADER, TOKEN_TYPE + accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .port(MockConstant.DEFAULT_APP_PORT)
                .post(url)
                .then()
                .log().all()
                .statusCode(code)
                .time(lessThan(MockConstant.DEFAULT_TIMEOUT));
    }

    protected String getAccessToken(String validUserDataJson) {
        return given()
                .log().all()
                .body(validUserDataJson)
                .when()
                .contentType(ContentType.JSON)
                .post(MockConstant.LOGIN_ENDPOINT)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().response()
                .jsonPath()
                .getString(ACCESS_TOKEN);
    }
}
