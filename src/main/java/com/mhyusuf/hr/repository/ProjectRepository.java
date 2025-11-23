package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Add custom query methods if needed
    boolean existsByName(String name);
}

