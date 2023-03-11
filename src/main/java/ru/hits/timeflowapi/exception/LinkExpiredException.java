package ru.hits.timeflowapi.exception;

/**
 * Исключение, которое следует выбрасывать, когда пользователь пытается перейти по ссылке для подтверждения почты,
 * а срок действия ссылки истёк.
 */
public class LinkExpiredException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public LinkExpiredException(String message) {
        super(message);
    }

}
