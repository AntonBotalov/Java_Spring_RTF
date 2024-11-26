package ru.botalov.ThirdAPP.service;


import org.springframework.stereotype.Service;
import ru.botalov.ThirdAPP.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
