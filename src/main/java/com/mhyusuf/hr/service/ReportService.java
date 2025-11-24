package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.ReportView;
import com.mhyusuf.hr.repository.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final TimeRecordRepository timeRecordRepository;

    @Autowired
    public ReportService(TimeRecordRepository timeRecordRepository) {
        this.timeRecordRepository = timeRecordRepository;
    }

    public List<ReportView> getReportData(LocalDateTime startDate, LocalDateTime endDate) {
        return timeRecordRepository.getReportData(startDate, endDate);
    }
}
