package com.mhyusuf.hr.controller;

import com.mhyusuf.hr.dto.EmployeeDto;
import com.mhyusuf.hr.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee API", description = "CRUD operations for Employee")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<EmployeeDto.Response> getAll() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public EmployeeDto.Response getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Create new employee")
    @PostMapping
    public ResponseEntity<EmployeeDto.Response> create(@Valid @RequestBody EmployeeDto.Request request) {
        EmployeeDto.Response response = employeeService.createEmployee(request);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Update employee")
    @PutMapping("/{id}")
    public EmployeeDto.Response update(@PathVariable Long id, @Valid @RequestBody EmployeeDto.Request request) {
        return employeeService.updateEmployee(id, request);
    }

    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
