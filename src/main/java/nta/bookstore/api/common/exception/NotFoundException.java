package nta.bookstore.api.common.exception;

import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.constant.ResponseConst;

@Getter
@Setter
public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super();
        this.code = ResponseConst.NOT_FOUND_CODE;
        this.message = message;
    }
}
