package com.mhyusuf.hr.runner;

import com.mhyusuf.hr.model.Employee;
import com.mhyusuf.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        createEmployeeIfNotExists("Tom");
        createEmployeeIfNotExists("Jerry");
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
