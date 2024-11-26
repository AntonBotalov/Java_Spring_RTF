package ru.botalov.ThirdAPP.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.botalov.ThirdAPP.exception.UnsupportedCodeException;
import ru.botalov.ThirdAPP.model.*;
import ru.botalov.ThirdAPP.service.ValidationService;
import ru.botalov.ThirdAPP.exception.ValidationFailedException;
import ru.botalov.ThirdAPP.util.DateTimeUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;

    @Autowired
    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("Received request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemName(request.getSystemName())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.validateUid(request.getUid());
            validationService.isValid(bindingResult);
        }catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSIPPORTED);
            log.error("Error occurred for request: {}", request);
        }catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Error occurred for request: {}", request);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Error occurred for request: {}", request);
        }

        log.info("Received response: {}", response);
        log.info(timeDifference(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String timeDifference (Request request){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        try {
            Date requestDate = simpleDateFormat.parse(request.getSystemTime());
            Date currentDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            long millisDifference = currentDate.getTime() - requestDate.getTime();

            return  "Сервис 2: Время с момента отправки запроса до получения ответа: " + millisDifference + " мс";

        } catch (Exception e) {
            return  "Ошибка: " + e.getMessage();
        }
    }
}
