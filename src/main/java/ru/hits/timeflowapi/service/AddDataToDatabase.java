package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.CustomRuntimeException;
import ru.hits.timeflowapi.model.entity.*;
import ru.hits.timeflowapi.repository.*;
import ru.hits.timeflowapi.util.DaysForCurrentYear;
import ru.hits.timeflowapi.util.WeeksForCurrentYear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddDataToDatabase {

    private final StudentGroupRepository studentGroupRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final TimeslotRepository timeslotRepository;
    private final WeekRepository weekRepository;

    private final WeeksForCurrentYear weeksForCurrentYear;
    private final DaysForCurrentYear daysForCurrentYear;
    private final DayRepository dayRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        addStudentGroups();
        addSubjects();
        addTeachers();
        addClassrooms();
        addTimeslots();
        addWeeks();
        addDays();
    }

    private void addStudentGroups() {
        studentGroupRepository.save(StudentGroupEntity.builder().number(971810).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(971811).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(971812).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(971901).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(971902).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(971905).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972001).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972002).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972005).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972006).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972101).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972105).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972110).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972201).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972202).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972203).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972205).build());
        studentGroupRepository.save(StudentGroupEntity.builder().number(972210).build());
    }

    private void addSubjects() {
        subjectRepository.save(SubjectEntity.builder().name("Иностранный язык").build());
        subjectRepository.save(SubjectEntity.builder().name("Разработка и анализ требований").build());
        subjectRepository.save(SubjectEntity.builder().name("Основы командной разработки").build());
        subjectRepository.save(SubjectEntity.builder().name("Основы машинного обучения").build());
        subjectRepository.save(SubjectEntity.builder().name("Тестирование программного обеспечения").build());
        subjectRepository.save(SubjectEntity.builder().name("Элективные дисциплины по физической культуре и спорту").build());
        subjectRepository.save(SubjectEntity.builder().name("Введение в проектную работу").build());
        subjectRepository.save(SubjectEntity.builder().name("Основы backend-разработки").build());
        subjectRepository.save(SubjectEntity.builder().name("Проектная разработка").build());
        subjectRepository.save(SubjectEntity.builder().name("Основы Web разработки (Frontend)").build());
        subjectRepository.save(SubjectEntity.builder().name("1С разработка").build());
        subjectRepository.save(SubjectEntity.builder().name("Разработка серверных приложений").build());
        subjectRepository.save(SubjectEntity.builder().name("Системный анализ").build());
        subjectRepository.save(SubjectEntity.builder().name("Web-разработка приложений").build());
        subjectRepository.save(SubjectEntity.builder().name("Разработка мобильных приложений").build());
        subjectRepository.save(SubjectEntity.builder().name("Машинное обучение").build());
    }

    private void addTeachers() {
        teacherRepository.save(TeacherEntity.builder().name("Иван").surname("Иванов").patronymic("Иванович").build());
        teacherRepository.save(TeacherEntity.builder().name("Станислав").surname("Романов").patronymic("Павлович").build());
        teacherRepository.save(TeacherEntity.builder().name("Богдан").surname("Якушев").patronymic("Натанович").build());
        teacherRepository.save(TeacherEntity.builder().name("Вилли").surname("Савин").patronymic("Максимович").build());
        teacherRepository.save(TeacherEntity.builder().name("Роман").surname("Громов").patronymic("Витальевич").build());
        teacherRepository.save(TeacherEntity.builder().name("Мартин").surname("Ермаков").patronymic("Евсеевич").build());
        teacherRepository.save(TeacherEntity.builder().name("Аркадий").surname("Сергеев").patronymic("Степанович").build());
        teacherRepository.save(TeacherEntity.builder().name("Мартын").surname("Тихонов").patronymic("Тихонович").build());
        teacherRepository.save(TeacherEntity.builder().name("Алина").surname("Фокина").patronymic("Богуславовна").build());
        teacherRepository.save(TeacherEntity.builder().name("Нина").surname("Колобова").patronymic("Тихоновна").build());
        teacherRepository.save(TeacherEntity.builder().name("Ульяна").surname("Казакова").patronymic("Васильевна").build());
        teacherRepository.save(TeacherEntity.builder().name("Наталья").surname("Титова").patronymic("Егоровна").build());
        teacherRepository.save(TeacherEntity.builder().name("Бронислава").surname("Силина").patronymic("Юрьевна").build());
        teacherRepository.save(TeacherEntity.builder().name("Ксения").surname("Сазонова").patronymic("Феликсовна").build());
        teacherRepository.save(TeacherEntity.builder().name("Санта").surname("Емельянова").patronymic("Иосифовна").build());
    }

    private void addClassrooms() {
        classroomRepository.save(ClassroomEntity.builder().number("204 (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("206 (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("208 (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("210 (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("212а (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("212б (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("214 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("216 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("217 (2) Компьютерный класс").build());
        classroomRepository.save(ClassroomEntity.builder().number("218 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("220 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("224 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("226 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("227 (2) Учебная аудитория").build());
        classroomRepository.save(ClassroomEntity.builder().number("228 (2) Учебная аудитория").build());
    }

    private void addTimeslots() {
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(1).beginTime("8:45").endTime("10:20").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(2).beginTime("10:35").endTime("12:10").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(3).beginTime("12:25").endTime("14:00").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(4).beginTime("14:45").endTime("16:20").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(5).beginTime("16:35").endTime("18:10").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(6).beginTime("18:25").endTime("20:00").build());
        timeslotRepository.save(TimeslotEntity.builder().sequenceNumber(7).beginTime("20:15").endTime("21:50").build());
    }

    private void addWeeks() {

        List<WeekEntity> weeks = weeksForCurrentYear.getWeeksForCurrentSchoolYear();

        for (WeekEntity week: weeks) {
            weekRepository.save(
                    WeekEntity.builder()
                    .sequenceNumber(week.getSequenceNumber())
                    .beginDate(week.getBeginDate())
                    .endDate(week.getEndDate())
                    .build());
        }

    }

    private void addDays() {

        List<DayEntity> days = daysForCurrentYear.getDaysForCurrentSchoolYear();

        for (DayEntity day: days) {
            dayRepository.save(DayEntity.builder().date(day.getDate()).week(day.getWeek()).build());
        }

    }

}
