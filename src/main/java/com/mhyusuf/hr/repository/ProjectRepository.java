package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    Optional<Project> findByName(String name);
}

