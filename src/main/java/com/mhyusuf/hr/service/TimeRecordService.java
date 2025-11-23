package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.TimeRecordDto;

import java.util.List;

public interface TimeRecordService {
    TimeRecordDto.Response create(TimeRecordDto.Request dto);
    TimeRecordDto.Response getById(Long id);
    List<TimeRecordDto.Response> getAll();
    TimeRecordDto.Response update(Long id, TimeRecordDto.Request dto);
    void delete(Long id);
}
