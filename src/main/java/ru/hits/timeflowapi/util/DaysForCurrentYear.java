package ru.hits.timeflowapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.timeflowapi.model.entity.DayEntity;
import ru.hits.timeflowapi.model.entity.WeekEntity;
import ru.hits.timeflowapi.service.WeekService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DaysForCurrentYear {

    private final WeekService weekService;

    public List<DayEntity> getDaysForCurrentSchoolYear() {

        List<WeekEntity> weeks = weekService.getAllWeeks();
        List<DayEntity> days = new ArrayList<>();

        for (WeekEntity week : weeks) {
            LocalDate currentDay = week.getBeginDate();
            while (!currentDay.isAfter(week.getEndDate())) {
                days.add(new DayEntity(currentDay, week));
                currentDay = currentDay.plusDays(1);
            }
        }

        return days;

    }

}
