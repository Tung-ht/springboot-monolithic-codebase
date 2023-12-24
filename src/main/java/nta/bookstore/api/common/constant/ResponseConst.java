package nta.bookstore.api.common.constant;

public class ResponseConst {
    // success
    public final static String SUCCESS_CODE = "00000";
    public final static String SUCCESS = "success";

    // unknown error
    public final static String UNAVAILABLE_CODE = "99999";
    public final static String UNAVAILABLE = "unavailable";

    // sql exception
    public final static String SQL_CONNECTION_ERROR_CODE = "00001";
    public final static String SQL_CONNECTION_ERROR = "sql_connection_error";

    // unauthorized
    public final static String UNAUTHORIZED_CODE = "00010";
    public final static String UNAUTHORIZED = "unauthorized";
    public final static String AUTHENTICATION_FAIL_CODE = "00011";
    public final static String AUTHENTICATION_FAIL = "authentication_fail";

    // null pointer exception
    public final static String NULL_POINTER_OR_BAD_REQUEST_CODE = "00020";
    public final static String NULL_POINTER = "null_pointer";

    // not found
    public final static String NOT_FOUND_CODE = "00030";
    public final static String USER_NOT_FOUND = "user_not_found";

    // duplicate
    public final static String DUPLICATE_CODE = "00030";
    public final static String EMAIL_ALREADY_USED = "email_already_used";

    // invalid data
    public final static String INVALID_DATA_CODE = "00040";
    public final static String INVALID_DATA = "invalid_data";
    public final static String WRONG_OTP = "wrong_otp";

    // image_error_code
    public final static String IMAGE_ERROR_CODE = "00050";
    public final static String IMAGE_ERROR_MESSAGE_NOT_EXIST ="Image not exist";
    public final static String IMAGE_ERROR_MESSAGE_GET_IMAGE ="Error occurs when get image";
}