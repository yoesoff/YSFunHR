package com.mhyusuf.hr.controller;

import com.mhyusuf.hr.repository.TimeRecordRepository;
import com.mhyusuf.hr.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReportController {

    private final TimeRecordRepository timeRecordRepository;

    @Autowired
    public ReportController(TimeRecordRepository timeRecordRepository) {
        this.timeRecordRepository = timeRecordRepository;
    }

    @GetMapping("/report")
    public String showReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {

        if (startDate == null || endDate == null) {
            startDate = LocalDateTime.now().minusMonths(1);
            endDate = LocalDateTime.now();
        }

        List<ReportDTO> reportData = timeRecordRepository.getReportData(startDate, endDate);
        model.addAttribute("reportData", reportData);
        return "work_hours_report";
    }
}
