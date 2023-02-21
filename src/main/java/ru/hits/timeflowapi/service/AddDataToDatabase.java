package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.repository.StudentGroupRepository;

@Service
@RequiredArgsConstructor
public class AddDataToDatabase {

    private final StudentGroupRepository studentGroupRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        addStudentGroups();
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

}
