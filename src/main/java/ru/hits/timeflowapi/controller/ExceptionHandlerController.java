package ru.hits.timeflowapi.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.hits.timeflowapi.exception.ConflictException;
import ru.hits.timeflowapi.exception.EmailAlreadyUsedException;
import ru.hits.timeflowapi.exception.NotFoundException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.model.dto.ApiError;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для обработки исключений. Здесь должны отлавливаться и обрабатываться все ошибки,
 * которые идут на контроллер.
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception,
                                                            WebRequest request
    ) {
        return new ResponseEntity<>(new ApiError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(ConflictException exception,
                                                            WebRequest request
    ) {
        return new ResponseEntity<>(new ApiError(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorizedException(UnauthorizedException exception,
                                                                WebRequest request
    ) {
        return new ResponseEntity<>(new ApiError(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}