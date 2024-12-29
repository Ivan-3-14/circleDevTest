package application.controller;

import application.entity.User;
import application.utils.MockConstant;
import application.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static application.utils.MockConstant.*;

class UserControllerTest extends BaseTest {

    @Test
    void whenGetAuthorById_thenStatus200AndReturnUserTest() {
        User user = MockUtils.getNewUser();
        Long id = userRepository.save(user).getId();
        checkStatusCodeAndBodyInGetRequest(
                GET_USER + id,
                HttpStatus.OK.value(),
                SCHEME_SOURCE_PATH + USER_SCHEME,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenCreateUser_thanStatus201Test() {
        User user = MockUtils.getNewUser();
        checkStatusCodeInPostRequest(
                CREATE_USER,
                HttpStatus.CREATED.value(),
                user,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenInvalidEmailCreateUser_thanStatus400Test() {
        User user = MockUtils.getNewUser();
        user.setEmail(null);
        checkStatusCodeInPostRequest(
                CREATE_USER,
                HttpStatus.BAD_REQUEST.value(),
                user,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenInvalidNameCreateUser_thanStatus400Test() {
        User user = MockUtils.getNewUser();
        user.setName(null);
        checkStatusCodeInPostRequest(
                CREATE_USER,
                HttpStatus.BAD_REQUEST.value(),
                user,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenInvalidPasswordCreateUser_thanStatus400Test() {
        User user = MockUtils.getNewUser();
        user.setPassword(USER_INVALID_PASS);
        checkStatusCodeInPostRequest(
                CREATE_USER,
                HttpStatus.BAD_REQUEST.value(),
                user,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenUpdateUser_thenStatus200() {
        User user = MockUtils.getNewUser();
        User updateUser = userRepository.save(user);
        updateUser.setSurname(USER_SURNAME);

        checkStatusCodeAndBodyInPostRequest(
                UPDATE_USER,
                HttpStatus.OK.value(),
                SCHEME_SOURCE_PATH + USER_SCHEME,
                updateUser,
                MockConstant.USER_CRED
        );
    }

    @Test
    void whenDeleteUser_thenStatus200() {
        User user = MockUtils.getNewUser();
        Long id = userRepository.save(user).getId();
        checkStatusCodeInDeleteRequest(
                DELETE_USER,
                HttpStatus.OK.value(),
                USER_ID_PARAM,
                id,
                MockConstant.ADMIN_CRED
        );
    }
}