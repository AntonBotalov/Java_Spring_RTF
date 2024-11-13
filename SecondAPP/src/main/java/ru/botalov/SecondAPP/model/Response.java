package ru.botalov.SecondAPP.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /*
     * Идентификатор пользователя
     */
    private String uid;
    /*
     * Идентификатор операции
     */
    private String operationUid;
    /*
     * Название системы
     */
    private Systems systemName;
    /*
     * Время исполнения запроса в системе
     */
    private String systemTime;
    /*
     * Идентификатор пользователя
     */
    private Codes code;
    /*
     * Премия за год
     */
    private Double annualBonus;
    /*
     * Код ошибки
     */
    private ErrorCodes errorCode;
    /*
     * Сообщение ошибки
     */
    private ErrorMessages errorMessage;
}
