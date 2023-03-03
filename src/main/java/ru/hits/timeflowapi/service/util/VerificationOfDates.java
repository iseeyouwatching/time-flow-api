package ru.hits.timeflowapi.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Сервис, предназначенный для проверки корректности введенных дат.
 */
@Service
@RequiredArgsConstructor
public class VerificationOfDates {

    private void checkDateDifference(LocalDate startDate, LocalDate endDate) {
        if (ChronoUnit.DAYS.between(startDate, endDate) != 5) {
            throw new BadRequestException("Даты " + startDate + " и " + endDate + " не образуют учебную неделю");
        }
    }

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

    public void checkDates(LocalDate startDate, LocalDate endDate) {
        checkDatesCorrectness(startDate, endDate);
        checkDateDifference(startDate, endDate);
    }


}
