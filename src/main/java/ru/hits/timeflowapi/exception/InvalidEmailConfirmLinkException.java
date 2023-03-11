package ru.hits.timeflowapi.exception;

/**
 * Исключение, которое следует кидать, когда ссылка для подтверждения почты пользователя является некорректной.
 */
public class InvalidEmailConfirmLinkException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public InvalidEmailConfirmLinkException(String message) {
        super(message);
    }

}
