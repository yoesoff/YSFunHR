package com.mhyusuf.hr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO untuk operasi Employee (Create, Update, Response).
 */
public class EmployeeDto {

    // --- REQUEST DTO ---

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "Employee's name cannot be empty.")
        @Size(max = 60, message = "Max 60 characters.")
        private String name;
    }

    // --- RESPONSE DTO ---

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String name;
    }
}