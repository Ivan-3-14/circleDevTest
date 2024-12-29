package application.utils;

public class MockConstant {
    public static final String BASE_URL = "http://localhost:8080";
    public static final int DEFAULT_APP_PORT = 8080;
    public static final String SCHEME_SOURCE_PATH = "test_schemes";
    public static final String USER_SCHEME = "/user.json";
    public static final String BOOK_SCHEME = "/book.json";
    public static final String AUTHOR_SCHEME = "/author.json";

    public static final String LOGIN_ENDPOINT = "/rest/library/auth/login";

    public static final String GET_USER = "/rest/library/user/get/?userId=";
    public static final String CREATE_USER = "/rest/library/user/create";
    public static final String UPDATE_USER = "/rest/library/user/update";
    public static final String DELETE_USER = "/rest/library/user/delete";

    public static final String GET_AUTHOR = "/rest/library/author/get?authorId=";
    public static final String CREATE_AUTHOR = "/rest/library/author/admin/create";
    public static final String UPDATE_AUTHOR = "/rest/library/author/admin/update";
    public static final String DELETE_AUTHOR = "/rest/library/author/admin/delete";

    public static final String CREATE_BOOK = "/rest/library/book/admin/create";
    public static final String UPDATE_BOOK = "/rest/library/book/admin/update";
    public static final String DELETE_BOOK = "/rest/library/book/admin/delete";
    public static final String GET_BOOKS = "/rest/library/book/get/books";
    public static final String GET_BOOK = "/rest/library/book/get/?bookId=";

    public static final String AUTHOR_NAME1 = "Joshua";
    public static final String AUTHOR_SURNAME1 = "Bloch";
    public static final String AUTHOR_NAME2 = "Herbert";
    public static final String AUTHOR_SURNAME2 = "Schildt";

    public static final String ADMIN_NAME = "Admin";
    public static final String ADMIN_EMAIL = "Admin@mail.ru";
    public static final String ADMIN_PASSWORD = "$2a$10$TyxLZQObe.wfygFv4l.qXOZNqRBtt9tAz8zChfaWjHBHSvdSv3jEu";

    public static final String USER_ID_PARAM = "userId";
    public static final String AUTHOR_ID_PARAM = "authorId";
    public static final String BOOK_ID_PARAM = "bookId";
    public static final String USER_INVALID_PASS = "111";

    public static final String ADMIN_CRED = "{\"email\": \"Admin@mail.ru\",\"name\": \"Admin\", \"password\": \"1111\"}";
    public static final String USER_CRED = "{ \"password\": \"1111\",\"name\": \"Ivan\",\"email\": \"1111@mail.ru\"}";

    public static final String USER_EMAIL = "1111@mail.ru";
    public static final String USER_NAME = "Ivan";
    public static final String USER_SURNAME = "Ivanov";
    public static final String USER_PASSWORD = "$2a$10$TyxLZQObe.wfygFv4l.qXOZNqRBtt9tAz8zChfaWjHBHSvdSv3jEu";

    public static final String NEW_USER_EMAIL = "1112@mail.ru";
    public static final String NEW_USER_NAME = "Peter";
    public static final String NEW_USER_SURNAME = "Parker";
    public static final String NEW_USER_PASSWORD = "$2a$10$TyxLZQObe.wfygFv4l.qXOZNqRBtt9tAz8zChfaWjHBHSvdSv3jEu";

    public static final String BOOK_NAME1 = "Effective Java";
    public static final Integer BOOK_YEAR_ISS1 = 2018;
    public static final String BOOK_NAME2 = "Java: A Beginner's Guide, Eighth Edition";
    public static final Integer BOOK_YEAR_ISS2 = 2019;

    public static final long DEFAULT_TIMEOUT = 1500L;
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String ACCESS_TOKEN = "accessToken";

    private MockConstant() {
    }
}
