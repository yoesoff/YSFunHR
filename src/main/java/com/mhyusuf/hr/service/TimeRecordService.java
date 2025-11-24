package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.TimeRecordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TimeRecordService {
    TimeRecordDto.Response create(TimeRecordDto.Request dto);
    TimeRecordDto.Response getById(Long id);
    public Page<TimeRecordDto.Response> getAll(String search, Pageable pageable);
    TimeRecordDto.Response update(Long id, TimeRecordDto.Request dto);
    void delete(Long id);
}
