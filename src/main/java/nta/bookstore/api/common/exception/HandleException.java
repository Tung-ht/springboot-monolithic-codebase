package nta.bookstore.api.common.exception;

import lombok.extern.slf4j.Slf4j;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.dto.AppResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler({NullPointerException.class})
    public AppResponse<?> handleNullPointerException(NullPointerException nullPointerException) {
        log.error("NullPointerException => rootCause: {}", Arrays.stream(nullPointerException.getStackTrace()).findFirst());
        log.error("NullPointerException => message: {}", nullPointerException.getMessage());
        return AppResponse.error(ResponseConst.NULL_POINTER_OR_BAD_REQUEST_CODE, ResponseConst.NULL_POINTER);
    }

    @ExceptionHandler({SQLException.class})
    public AppResponse<?> handleSQLException(SQLException ex) {
        log.error("SQLException => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("SQLException => message: {}", ex.getMessage());
        return AppResponse.error(ResponseConst.SQL_CONNECTION_ERROR_CODE, ResponseConst.SQL_CONNECTION_ERROR);
    }

    @ExceptionHandler(AppException.class)
    public AppResponse<?> handleGeneralException(AppException ex) {
        return AppResponse.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public AppResponse<?> handleException(Exception ex) {
        log.error("Exception => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("Exception => message: {}", ex.getMessage());
        return AppResponse.error(ResponseConst.UNAVAILABLE_CODE, ResponseConst.UNAVAILABLE);
    }
}
