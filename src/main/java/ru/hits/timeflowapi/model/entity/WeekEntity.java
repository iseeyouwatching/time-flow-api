package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "week")
public class WeekEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "sequence_number")
    private int sequenceNumber;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "week")
    private List<DayEntity> days;

    @OneToMany(mappedBy = "week")
    private List<LessonEntity> lessons;

    public WeekEntity(int sequenceNumber, LocalDate beginDate, LocalDate endDate) {
        this.sequenceNumber = sequenceNumber;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}
