package com.mhyusuf.hr.service;

import com.mhyusuf.hr.dto.ProjectRequest;
import com.mhyusuf.hr.dto.ProjectResponse;
import com.mhyusuf.hr.exception.DuplicateResourceException;
import com.mhyusuf.hr.exception.ResourceNotFoundException;
import com.mhyusuf.hr.model.Project;
import com.mhyusuf.hr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements  ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Page<ProjectResponse> getAllProjects(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projectPage;
        if (search != null && !search.isEmpty()) {
            projectPage = projectRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            projectPage = projectRepository.findAll(pageable);
        }
        return projectPage.map(this::toResponse);
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return toResponse(project);
    }

    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        if (projectRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Project with name already exists: " + request.getName());
        }
        Project project = Project.builder().name(request.getName()).build();
        project = projectRepository.save(project);
        return toResponse(project);
    }

    @Transactional
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        project.setName(request.getName());
        project = projectRepository.save(project);
        return toResponse(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private ProjectResponse toResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        return response;
    }
}
