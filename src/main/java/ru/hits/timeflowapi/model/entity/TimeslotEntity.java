package ru.hits.timeflowapi.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "timeslot")
public class TimeslotEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "sequence_number", unique = true)
    private int sequenceNumber;

    @Column(name = "begin_time")
    @Temporal(TemporalType.DATE)
    private Date beginTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.DATE)
    private Date endTime;

    @OneToMany(mappedBy = "timeslot")
    private List<LessonEntity> lessons;

}
