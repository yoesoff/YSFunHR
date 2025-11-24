package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.model.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
    boolean existsByEmployeeIdAndProjectIdAndTimeFromAndTimeTo(
            Long employeeId, Long projectId, LocalDateTime timeFrom, LocalDateTime timeTo
    );
    Page<TimeRecord> findByEmployee_NameContainingIgnoreCaseOrProject_NameContainingIgnoreCase(
            String employeeName, String projectName, Pageable pageable
    );
}
