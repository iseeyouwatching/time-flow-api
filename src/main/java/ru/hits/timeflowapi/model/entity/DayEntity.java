package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "day")
public class DayEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    private WeekEntity week;

    public DayEntity(LocalDate date, WeekEntity week) {
        this.date = date;
        this.week = week;
    }

}
