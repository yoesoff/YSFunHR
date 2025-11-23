package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Add custom query methods if needed
    boolean existsByName(String name);
}
