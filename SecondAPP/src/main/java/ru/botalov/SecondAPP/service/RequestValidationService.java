package ru.botalov.SecondAPP.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.botalov.SecondAPP.exception.UnsupportedCodeException;
import ru.botalov.SecondAPP.exception.ValidationFailedException;

import java.util.Objects;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    @Override
    public void validateUid(String uid) throws UnsupportedCodeException {
        if (Objects.equals(uid, "123")){
            throw new UnsupportedCodeException(uid);
        }
    }
}
