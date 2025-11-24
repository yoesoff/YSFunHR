package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.dto.ReportView;
import com.mhyusuf.hr.model.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
    boolean existsByEmployee_IdAndProject_IdAndTimeFromAndTimeTo(
            Long employeeId, Long projectId, LocalDateTime timeFrom, LocalDateTime timeTo
    );

    Page<TimeRecord> findByEmployee_NameContainingIgnoreCaseOrProject_NameContainingIgnoreCase(
            String employeeName, String projectName, Pageable pageable
    );

    @Query(value = """
        SELECT 
            e.name AS employeeName,
            p.name AS projectName,
            SUM(EXTRACT(EPOCH FROM (tr.time_to - tr.time_from)) / 3600) AS totalHours
        FROM time_record tr
        JOIN employee e ON e.id = tr.employee_id
        JOIN project p ON p.id = tr.project_id
        WHERE tr.time_from >= :startDate AND tr.time_from < :endDate
        GROUP BY tr.employee_id, e.name, tr.project_id, p.name
        ORDER BY e.name, p.name
    """, nativeQuery = true)
    List<ReportView> getReportData(@Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate);
}
