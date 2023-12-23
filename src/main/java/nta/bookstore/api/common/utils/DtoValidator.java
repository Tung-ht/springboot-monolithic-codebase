package nta.bookstore.api.common.utils;

import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Component
public class DtoValidator {
    @Autowired
    private Validator validator;

    public void validate(Object dto) {
        List<String> errors = new ArrayList<>();
        validator.validate(dto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new AppException(ResponseConst.INVALID_DATA_CODE, errors.get(0));
        }
    }
}
