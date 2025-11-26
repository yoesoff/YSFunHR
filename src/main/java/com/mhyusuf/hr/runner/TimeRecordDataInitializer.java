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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * This data initializer creates TimeRecord entries in the database if they do not already exist.
 * It assigns specific time records to "Tom" and "Jerry", and then creates 1000 additional time records
 * for users "user1" through "user1000" with random project assignments and work times.
 */
@Order(Integer.MAX_VALUE)
@Component
public class TimeRecordDataInitializer implements CommandLineRunner {

    @Autowired
    private TimeRecordRepository timeRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private static final String[] PROJECT_NAMES =
            java.util.stream.IntStream.rangeClosed('A', 'Z')
                    .mapToObj(i -> "Sample Project " + (char) i)
                    .toArray(String[]::new);

    @Override
    public void run(String... args) {
        // Existing Tom and Jerry assignments
        createTimeRecordIfNotExists("Tom", "Sample Project A",
                LocalDateTime.of(2024, 2, 1, 8, 0, 0),
                LocalDateTime.of(2024, 2, 1, 17, 0, 0));
        createTimeRecordIfNotExists("Jerry", "Sample Project B",
                LocalDateTime.of(2024, 2, 1, 9, 0, 0),
                LocalDateTime.of(2024, 2, 1, 18, 30, 0));
        createTimeRecordIfNotExists("Tom", "Sample Project A",
                LocalDateTime.of(2024, 2, 2, 8, 15, 0),
                LocalDateTime.of(2024, 2, 2, 17, 10, 0));

        // Assign user1-user1000 to random projects and dates
        Random random = new Random();
        for (int i = 1; i <= 1000; i++) {
            String userName = "user" + i;
            String projectName = PROJECT_NAMES[random.nextInt(PROJECT_NAMES.length)];
            LocalDate randomDate = getRandomDateBetween(
                    LocalDate.of(2025, 12, 1),
                    LocalDate.of(2026, 12, 31),
                    random
            );
            // Random work start between 7:00 and 10:00
            int startHour = 7 + random.nextInt(4);
            int startMinute = random.nextInt(60);
            LocalDateTime from = LocalDateTime.of(randomDate, LocalTime.of(startHour, startMinute));
            // Random work duration between 7 and 10 hours
            int workHours = 7 + random.nextInt(4);
            int workMinutes = random.nextInt(60);
            LocalDateTime to = from.plusHours(workHours).plusMinutes(workMinutes);

            createTimeRecordIfNotExists(userName, projectName, from, to);
        }
    }

    private LocalDate getRandomDateBetween(LocalDate start, LocalDate end, Random random) {
        int days = (int) (end.toEpochDay() - start.toEpochDay());
        return start.plusDays(random.nextInt(days + 1));
    }

    private void createTimeRecordIfNotExists(String employeeName, String projectName, LocalDateTime from, LocalDateTime to) {
        Optional<Employee> employeeOpt = employeeRepository.findAll()
                .stream().filter(e -> e.getName().equals(employeeName)).findFirst();
        Optional<Project> projectOpt = projectRepository.findAll()
                .stream().filter(p -> p.getName().equals(projectName)).findFirst();

        if (employeeOpt.isPresent() && projectOpt.isPresent()) {
            Long employeeId = employeeOpt.get().getId();
            Long projectId = projectOpt.get().getId();
            boolean exists = timeRecordRepository.existsByEmployee_IdAndProject_IdAndTimeFromAndTimeTo(
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
