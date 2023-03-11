package ru.hits.timeflowapi.exception;

/**
 * Исключение для внутренних ошибок сервера.
 */
public class InternalException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public InternalException(String message) {
        super(message);
    }

}
