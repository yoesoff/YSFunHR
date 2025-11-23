package com.mhyusuf.hr.runner;

import com.mhyusuf.hr.model.Employee;
import com.mhyusuf.hr.model.Project;
import com.mhyusuf.hr.model.TimeRecord;
import com.mhyusuf.hr.repository.EmployeeRepository;
import com.mhyusuf.hr.repository.ProjectRepository;
import com.mhyusuf.hr.repository.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Order(Integer.MAX_VALUE)
@Component
public class TimeRecordDataInitializer implements CommandLineRunner {

    @Autowired
    private TimeRecordRepository timeRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) {
        createTimeRecordIfNotExists("Tom", "Sample Project A",
                LocalDateTime.of(2024, 2, 1, 8, 0, 0),
                LocalDateTime.of(2024, 2, 1, 17, 0, 0));
        createTimeRecordIfNotExists("Jerry", "Sample Project B",
                LocalDateTime.of(2024, 2, 1, 9, 0, 0),
                LocalDateTime.of(2024, 2, 1, 18, 30, 0));
        createTimeRecordIfNotExists("Tom", "Sample Project A",
                LocalDateTime.of(2024, 2, 2, 8, 15, 0),
                LocalDateTime.of(2024, 2, 2, 17, 10, 0));
    }

    private void createTimeRecordIfNotExists(String employeeName, String projectName, LocalDateTime from, LocalDateTime to) {
        Optional<Employee> employeeOpt = employeeRepository.findAll()
                .stream().filter(e -> e.getName().equals(employeeName)).findFirst();
        Optional<Project> projectOpt = projectRepository.findAll()
                .stream().filter(p -> p.getName().equals(projectName)).findFirst();

        if (employeeOpt.isPresent() && projectOpt.isPresent()) {
            Long employeeId = employeeOpt.get().getId();
            Long projectId = projectOpt.get().getId();
            boolean exists = timeRecordRepository.existsByEmployeeIdAndProjectIdAndTimeFromAndTimeTo(
                    employeeId, projectId, from, to
            );
            if (!exists) {
                TimeRecord record = TimeRecord.builder()
                        .employee(employeeOpt.get())
                        .project(projectOpt.get())
                        .timeFrom(from)
                        .timeTo(to)
                        .build();
                timeRecordRepository.save(record);
            }
        }
    }
}
