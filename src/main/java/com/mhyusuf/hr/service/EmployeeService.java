package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto.Response> getAllEmployees();
    EmployeeDto.Response getEmployeeById(Long id);
    EmployeeDto.Response createEmployee(EmployeeDto.Request request);
    EmployeeDto.Response updateEmployee(Long id, EmployeeDto.Request request);
    void deleteEmployee(Long id);
}
