package com.mhyusuf.hr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Project entity.
 */
public class ProjectDto {

    // --- REQUEST DTO ---

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "Project's name cannot be empty.")
        @Size(max = 200, message = "Mxx 200 character.")
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