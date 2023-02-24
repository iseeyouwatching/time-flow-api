package ru.hits.timeflowapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.model.entity.EmployeeDetailsEntity;
import ru.hits.timeflowapi.model.entity.EmployeePostEntity;
import ru.hits.timeflowapi.model.entity.StudentGroupEntity;
import ru.hits.timeflowapi.model.entity.UserEntity;
import ru.hits.timeflowapi.model.enumeration.AccountStatus;
import ru.hits.timeflowapi.model.enumeration.Role;
import ru.hits.timeflowapi.model.enumeration.Sex;
import ru.hits.timeflowapi.repository.EmployeeDetailsRepository;
import ru.hits.timeflowapi.repository.EmployeePostRepository;
import ru.hits.timeflowapi.repository.StudentGroupRepository;
import ru.hits.timeflowapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddDataToDatabase {

    private final StudentGroupRepository studentGroupRepository;
    private final EmployeePostRepository employeePostRepository;
    private final UserRepository userRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        addStudentGroups();
        addEmployeePosts();
        addAdmin();
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

    private void addEmployeePosts() {
        employeePostRepository.save(
                EmployeePostEntity
                        .builder()
                        .postName("Составитель расписаний")
                        .postRole("ROLE_SCHEDULE_MAKER")
                        .build()
        );

        employeePostRepository.save(
                EmployeePostEntity
                        .builder()
                        .postName("Администратор")
                        .postRole("ROLE_ADMIN")
                        .build()
        );

        employeePostRepository.save(
                EmployeePostEntity
                        .builder()
                        .postName("Преподаватель")
                        .postRole("ROLE_TEACHER")
                        .build()
        );
    }

    private void addAdmin() {
        Optional<EmployeePostEntity> roleAdmin = employeePostRepository.findByPostRole("ROLE_ADMIN");

        if (roleAdmin.isEmpty()) {
            return;
        }

        UserEntity user = UserEntity
                .builder()
                .email("email@gmail.com")
                .role(Role.ROLE_EMPLOYEE)
                .name("Иван")
                .surname("Иванов")
                .patronymic("Иванович")
                .accountStatus(AccountStatus.ACTIVATE)
                .password(passwordEncoder.encode("root"))
                .sex(Sex.MALE)
                .build();

        user = userRepository.save(user);

        employeeDetailsRepository.save(
                EmployeeDetailsEntity
                        .builder()
                        .user(user)
                        .posts(List.of(roleAdmin.get()))
                        .contactNumber("000000")
                        .build()
        );

    }

}
