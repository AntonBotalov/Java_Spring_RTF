package ru.botalov.SecondAPP.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /*
     * Идентификатор пользователя
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "UID должен быть до 32 символов")
    private String uid;
    /*
     * Идентификатор операции
     */
    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max = 32, message = "operationUid должен быть до 32 символов")
    private String operationUid;
    /*
     * Название системы
     */
    private Systems systemName;
    /*
     * Время исполнения запроса в системе
     */
    @NotBlank(message = "systemTime не может быть пустым")
    private String systemTime;
    /*
     * Содержание
     */
    private String source;
    /*
     * Позиция сотрудника
     */
    private Positions positions;
    /*
     * Зарплата сотрудника
     */
    private Double salary;
    /*
     * Бонус коэффициент сотрудника
     */
    private Double bonus;
    /*
     * Количество рабочих дней в году
     */
    private int workDays;
    /*
     * Идентификатор коммуникации
     */
    @Min(value = 1, message = "communicationId должен быть не менее 1 символа")
    @Max(value = 100000, message = "communicationId должен быть не более 100000 символов")
    private Integer communicationId;
    /*
     * Номер шаблона
     */
    private Integer templateId;
    /*
     * Код продукта
     */
    private Integer productCode;
    /*
     * Смс код
     */
    private Integer smsCode;

    @Override
    public String toString() {
        return "{" + "uid='" + uid + '\'' + ", opertionUid='" + operationUid + '\'' + ", systemName='" + systemName + '\'' + ", systemTime='" + systemTime + '\'' + ", source='" + source + '\'' + ", communicationId=" + communicationId + ", templateId=" + templateId + ", productCode=" + productCode + ", smsCode=" + smsCode + '}';
    }
}



