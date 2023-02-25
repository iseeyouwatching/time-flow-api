package ru.hits.timeflowapi.util;

import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.entity.WeekEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeeksForCurrentYear {

    public List<WeekEntity> getWeeksForCurrentSchoolYear() {
        List<WeekEntity> weeks = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2022, Month.AUGUST, 29);
        LocalDate endDate = LocalDate.of(2023, Month.AUGUST, 26);

        LocalDate currentWeekStart = startDate;
        int counter = 1;
        while (currentWeekStart.isBefore(endDate)) {
            LocalDate currentWeekEnd = currentWeekStart.plusDays(5);
            if (currentWeekEnd.isAfter(endDate)) {
                currentWeekEnd = endDate;
            }
            weeks.add(new WeekEntity(counter++, currentWeekStart, currentWeekEnd));
            currentWeekStart = currentWeekEnd.plusDays(2);
        }

        return weeks;
    }

}
