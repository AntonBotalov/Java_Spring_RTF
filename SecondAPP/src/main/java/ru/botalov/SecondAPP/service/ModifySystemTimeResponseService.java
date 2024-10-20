package ru.botalov.SecondAPP.service;

import org.springframework.stereotype.Service;
import ru.botalov.SecondAPP.model.Response;
import ru.botalov.SecondAPP.service.ModifyResponseService;
import ru.botalov.SecondAPP.util.DateTimeUtil;

import java.util.Date;


@Service
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {

        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        return response;
    }
}
