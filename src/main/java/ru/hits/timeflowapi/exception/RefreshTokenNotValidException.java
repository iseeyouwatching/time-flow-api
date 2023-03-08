package ru.hits.timeflowapi.exception;

/**
 * Исключение, которое следует бросать, когда какие-то проблемы с {@code refresh} токеном.
 * Например, если токен: отсутствует, истёк, подделан.
 */
public class RefreshTokenNotValidException extends RuntimeException {

    public RefreshTokenNotValidException(String message) {
        super(message);
    }

}
