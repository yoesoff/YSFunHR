package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<EmployeeDto.Response> getAllEmployees(int page, int size, String search);
    EmployeeDto.Response getEmployeeById(Long id);
    EmployeeDto.Response createEmployee(EmployeeDto.Request request);
    EmployeeDto.Response updateEmployee(Long id, EmployeeDto.Request request);
    void deleteEmployee(Long id);
}
