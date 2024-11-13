package ru.botalov.SecondAPP.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.botalov.SecondAPP.model.Request;
import ru.botalov.SecondAPP.model.Systems;
import ru.botalov.SecondAPP.util.DateTimeUtil;

import java.util.Date;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService{
    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.ERP);
        request.setSource("Изменил поле");
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {

                });
    }
}
