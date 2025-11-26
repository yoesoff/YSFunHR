package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.EmployeeDto;
import com.mhyusuf.hr.exception.DuplicateResourceException;
import com.mhyusuf.hr.model.Employee;
import com.mhyusuf.hr.repository.EmployeeRepository;
import com.mhyusuf.setting.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeDto.Response> getAllEmployees(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage;
        if (search != null && !search.isEmpty()) {
            employeePage = employeeRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            employeePage = employeeRepository.findAll(pageable);
        }
        return employeePage.map(emp -> EmployeeDto.Response.builder()
                .id(emp.getId())
                .name(emp.getName())
                .build());
    }

    @Override
    public EmployeeDto.Response getEmployeeById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return EmployeeDto.Response.builder()
                .id(emp.getId())
                .name(emp.getName())
                .build();
    }

    @Override
    public EmployeeDto.Response createEmployee(EmployeeDto.Request request) {
        if (employeeRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Employee name already exists");
        }
        Employee emp = Employee.builder()
                .name(request.getName())
                .build();
        emp = employeeRepository.save(emp);
        return EmployeeDto.Response.builder()
                .id(emp.getId())
                .name(emp.getName())
                .build();
    }

    @Override
    public EmployeeDto.Response updateEmployee(Long id, EmployeeDto.Request request) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!emp.getName().equals(request.getName()) && employeeRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Employee name already exists");
        }
        emp.setName(request.getName());
        emp = employeeRepository.save(emp);
        return EmployeeDto.Response.builder()
                .id(emp.getId())
                .name(emp.getName())
                .build();
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
