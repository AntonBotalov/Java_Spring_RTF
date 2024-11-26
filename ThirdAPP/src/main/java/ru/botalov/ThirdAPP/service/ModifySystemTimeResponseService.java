package ru.botalov.ThirdAPP.service;

import org.springframework.stereotype.Service;
import ru.botalov.ThirdAPP.model.Response;
import ru.botalov.ThirdAPP.util.DateTimeUtil;

import java.util.Date;


@Service
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {

        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        return response;
    }
}
