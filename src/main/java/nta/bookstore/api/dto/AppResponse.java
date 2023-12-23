package nta.bookstore.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import nta.bookstore.api.common.constant.ResponseConst;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T> implements Serializable {
    private String statusCode;
    private String message;
    private T data;

    public static <T> AppResponse<T> ok() {
        return AppResponse.<T>builder()
                .statusCode(ResponseConst.SUCCESS_CODE)
                .message(ResponseConst.SUCCESS)
                .build();
    }

    public static <T> AppResponse<T> ok(T data) {
        return AppResponse.<T>builder()
                .statusCode(ResponseConst.SUCCESS_CODE)
                .message(ResponseConst.SUCCESS)
                .data(data)
                .build();
    }

    public static <T> AppResponse<T> error() {
        return AppResponse.<T>builder()
                .statusCode(ResponseConst.UNAVAILABLE_CODE)
                .message(ResponseConst.UNAVAILABLE)
                .build();
    }

    public static <T> AppResponse<T> error(String statusCode, String message) {
        return AppResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}