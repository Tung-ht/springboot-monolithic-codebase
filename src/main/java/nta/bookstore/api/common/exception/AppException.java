package nta.bookstore.api.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppException extends RuntimeException {
    protected String code;
    protected String message;

    public AppException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
