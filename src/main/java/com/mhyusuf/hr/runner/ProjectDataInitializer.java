package com.mhyusuf.hr.runner;

import com.mhyusuf.hr.model.Project;
import com.mhyusuf.hr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectDataInitializer implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        for (char c = 'A'; c <= 'Z'; c++) {
            createProjectIfNotExists("Sample Project " + c);
        }
    }

    private void createProjectIfNotExists(String name) {
        if (!projectRepository.existsByName(name)) {
            Project project = Project.builder()
                    .name(name)
                    .build();
            projectRepository.save(project);
        }
    }
}
