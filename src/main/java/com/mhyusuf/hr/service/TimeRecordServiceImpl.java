package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.TimeRecordDto;
import com.mhyusuf.hr.model.Employee;
import com.mhyusuf.hr.model.Project;
import com.mhyusuf.hr.model.TimeRecord;
import com.mhyusuf.hr.repository.EmployeeRepository;
import com.mhyusuf.hr.repository.ProjectRepository;
import com.mhyusuf.hr.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeRecordServiceImpl implements TimeRecordService {
    private final TimeRecordRepository timeRecordRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Override
    public TimeRecordDto.Response create(TimeRecordDto.Request dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow();
        TimeRecord timeRecord = TimeRecord.builder()
                .employee(employee)
                .project(project)
                .timeFrom(dto.getTimeFrom())
                .timeTo(dto.getTimeTo())
                .build();
        timeRecord = timeRecordRepository.save(timeRecord);
        return toResponseDto(timeRecord);
    }

    @Override
    public TimeRecordDto.Response getById(Long id) {
        return timeRecordRepository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow();
    }

    @Override
    public Page<TimeRecordDto.Response> getAll(String search, Pageable pageable) {
        Page<TimeRecord> page;
        if (search != null && !search.isEmpty()) {
            page = timeRecordRepository.findByEmployee_NameContainingIgnoreCaseOrProject_NameContainingIgnoreCase(
                    search, search, pageable
            );
        } else {
            page = timeRecordRepository.findAll(pageable);
        }
        return page.map(this::toResponseDto);
    }

    @Override
    public TimeRecordDto.Response update(Long id, TimeRecordDto.Request dto) {
        TimeRecord timeRecord = timeRecordRepository.findById(id).orElseThrow();
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow();
        timeRecord.setEmployee(employee);
        timeRecord.setProject(project);
        timeRecord.setTimeFrom(dto.getTimeFrom());
        timeRecord.setTimeTo(dto.getTimeTo());
        return toResponseDto(timeRecordRepository.save(timeRecord));
    }

    @Override
    public void delete(Long id) {
        timeRecordRepository.deleteById(id);
    }

    private TimeRecordDto.Response toResponseDto(TimeRecord tr) {
        return TimeRecordDto.Response.builder()
                .id(tr.getId())
                .employeeId(tr.getEmployee().getId())
                .projectId(tr.getProject().getId())
                .timeFrom(tr.getTimeFrom())
                .timeTo(tr.getTimeTo())
                // add more fields if needed
                .build();
    }
}
