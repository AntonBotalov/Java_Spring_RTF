package ru.botalov.SecondAPP.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.botalov.SecondAPP.exception.UnsupportedCodeException;
import ru.botalov.SecondAPP.model.*;
import ru.botalov.SecondAPP.service.*;
import ru.botalov.SecondAPP.exception.ValidationFailedException;
import ru.botalov.SecondAPP.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService, AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("Received request: {}", request);

        Response response = createResponse(request);

        try {
            validationService.validateUid(request.getUid());
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            handleException(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSIPPORTED, request, e);
        } catch (ValidationFailedException e) {
            handleException(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, request, e);
        } catch (Exception e) {
            handleException(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, request, e);
        }

        log.info("Received response: {}", response);
        modifyResponseService.modify(response);
        modifyRequestService.modify(request);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }

    private Response createResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemName(Systems.CRM)
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .annualBonus(annualBonusService.calculate(request.getPositions(), request.getSalary(), request.getBonus(), request.getWorkDays()))
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void handleException(Response response, ErrorCodes errorCode, ErrorMessages errorMessage, Request request, Exception e) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        log.error("Error occurred for request: {}", request, e);
    }
}

