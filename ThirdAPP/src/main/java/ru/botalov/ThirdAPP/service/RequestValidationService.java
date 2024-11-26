package ru.botalov.ThirdAPP.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.botalov.ThirdAPP.exception.UnsupportedCodeException;
import ru.botalov.ThirdAPP.exception.ValidationFailedException;

import java.util.Objects;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult.getFieldError().getDefaultMessage());
        }
    }

    @Override
    public void validateUid(String uid) throws UnsupportedCodeException {
        if (Objects.equals(uid, "123")){
            throw new UnsupportedCodeException("Unsupported uid value: 123");
        }
    }
}
