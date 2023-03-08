package ru.hits.timeflowapi.exception;

/**
 * Исключение, которое следует бросать, когда какие-то проблемы с {@code access} токеном.
 * Например, если токен: отсутствует, истёк, подделан.
 */
public class AccessTokenNotValidException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public AccessTokenNotValidException(String message) {
        super(message);
    }

}
