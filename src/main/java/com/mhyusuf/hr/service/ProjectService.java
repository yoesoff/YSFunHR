package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.ProjectRequest;
import com.mhyusuf.hr.dto.ProjectResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    Page<ProjectResponse> getAllProjects(int page, int size, String search);
    ProjectResponse getProjectById(Long id);
    ProjectResponse createProject(ProjectRequest request);
    ProjectResponse updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
}
