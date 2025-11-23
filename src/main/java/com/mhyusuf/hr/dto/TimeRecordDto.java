package com.mhyusuf.hr.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for TimeRecord operations (Create, Update, Response).
 */
public class TimeRecordDto {

    // --- REQUEST DTO ---

    /**
     * DTO for creating or updating a TimeRecord.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "Employee ID must not be null.")
        private Long employeeId;

        @NotNull(message = "Project ID must not be null.")
        private Long projectId;

        @NotNull(message = "Start time must not be null.")
        private LocalDateTime timeFrom;

        @NotNull(message = "End time must not be null.")
        private LocalDateTime timeTo;
    }

    // --- RESPONSE DTO ---

    /**
     * DTO for returning TimeRecord data.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private Long employeeId;
        private Long projectId;
        private LocalDateTime timeFrom;
        private LocalDateTime timeTo;

        // Optional: More informative response
        private String employeeName;
        private String projectName;
    }
}
