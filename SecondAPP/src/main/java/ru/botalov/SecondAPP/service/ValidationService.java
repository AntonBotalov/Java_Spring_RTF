package ru.botalov.SecondAPP.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.botalov.SecondAPP.exception.UnsupportedCodeException;
import ru.botalov.SecondAPP.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void validateUid(String uid) throws UnsupportedCodeException;
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}

