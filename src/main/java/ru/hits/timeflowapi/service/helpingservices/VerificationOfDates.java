package ru.hits.timeflowapi.service.helpingservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;

import java.time.LocalDate;

/**
 * Сервис, предназначенный для проверки корректности введенных дат.
 */
@Service
@RequiredArgsConstructor
public class VerificationOfDates {

    /**
     * Метод для проверки корректности стартовой и конечной даты.
     *
     * @param startDate стартовая дата учебной недели.
     * @param endDate   конечная дата учебной недели.
     * @throws BadRequestException исключение, которое выбрасывается, если введены некорректные даты.
     */
    private void checkDatesCorrectness(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadRequestException(
                    "Стартовая дата " + startDate + " не может быть позже конечной даты " + endDate);
        }

        if (endDate.isBefore(startDate)) {
            throw new BadRequestException(
                    "Конечная дата " + endDate + " не может быть раньше конечной даты " + startDate);
        }
    }

    /**
     * Обобщающий метод для проверки стартовой и конечной даты.
     *
     * @param startDate стартовая дата учебной недели.
     * @param endDate   конечная дата учебной недели.
     */
    public void checkDates(LocalDate startDate, LocalDate endDate) {
        checkDatesCorrectness(startDate, endDate);
    }


}
