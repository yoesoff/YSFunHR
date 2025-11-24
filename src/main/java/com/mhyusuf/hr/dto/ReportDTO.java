package com.mhyusuf.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private String employeeName;
    private String projectName;
    private Double totalHours;

    public ReportDTO(String employeeName, String projectName, double totalHours) {
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.totalHours = totalHours;
    }
}
