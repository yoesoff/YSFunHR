package com.mhyusuf.hr.runner;

import com.mhyusuf.hr.model.Employee;
import com.mhyusuf.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
 This data initializer creates Employee entries in the database if they do not already exist.
 It adds two specific employees, "Tom" and "Jerry", and then creates 1000 additional employees
 named "user1" through "user1000".
*/
@Component
public class EmployeeDataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        createEmployeeIfNotExists("Tom");    // No 1
        createEmployeeIfNotExists("Jerry");  // No 2
        for (int i = 1; i <= 1000; i++) {
            createEmployeeIfNotExists("user" + i); // user1 - user1000
        }
    }

    private void createEmployeeIfNotExists(String name) {
        if (!employeeRepository.existsByName(name)) {
            Employee employee = Employee.builder()
                    .name(name)
                    .build();
            employeeRepository.save(employee);
        }
    }
}
