package ru.botalov.SecondAPP.service;

import org.springframework.stereotype.Service;
import ru.botalov.SecondAPP.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
