package ru.botalov.ThirdAPP.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.botalov.ThirdAPP.exception.UnsupportedCodeException;
import ru.botalov.ThirdAPP.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void validateUid(String uid) throws UnsupportedCodeException;
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}

