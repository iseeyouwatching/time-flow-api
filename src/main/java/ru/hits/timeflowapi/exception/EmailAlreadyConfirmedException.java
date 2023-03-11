package ru.hits.timeflowapi.exception;

/**
 * Исключение, которое следует выбрасывать, когда пользователь пытается подтвердить, уже подтвержденную почту.
 */
public class EmailAlreadyConfirmedException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public EmailAlreadyConfirmedException(String message) {
        super(message);
    }

}
