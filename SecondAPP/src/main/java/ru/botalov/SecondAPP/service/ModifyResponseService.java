package ru.botalov.SecondAPP.service;


import org.springframework.stereotype.Service;
import ru.botalov.SecondAPP.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
