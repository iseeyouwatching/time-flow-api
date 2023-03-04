package ru.hits.timeflowapi.exception;


/**
 * Класс кастомной ошибки.
 * BadRequestException используется при некорректных входных данных в запросе
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
