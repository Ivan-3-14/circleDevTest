package application.utils;

/**
 * This class represents all the constants in the application
 */

public class Constant {

    public final static String NAME_CANNOT_BE_EMPTY = "Name can't be null or empty!";
    public final static String SURNAME_CANNOT_BE_EMPTY = "Surname can't be null or empty!";
    public static final String EMAIL_CANNOT_BE_NULL_OR_EMPTY = "Email cannot be null or empty!";
    public static final String PASSWORD_CANNOT_BE_NULL_OR_EMPTY = "Password cannot be null or empty!";
    public final static String ID_CANNOT_BE_NULL = "Id cannot be null!";
    public final static String ID_CANNOT_BE_LESS_1 = "ID cannot be less than 1";
    public final static String PASSWORD_SIZE = "At least 4 characters";
    public static final String ROLE_CANNOT_BE_NULL = "Role cannot be null!";
    public static final String THE_ROLE_SET_CANNOT_BE_EMPTY = "The role set cannot be empty! You must select at least one user role!";
    public static final long MIN_ID = 1L;
    public static final String REGEXP_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email format. Valid format: username@domain.com";
    public static final String AUTH_USER_NOT_FOUND = "There is no such user with this email address";

}
