package com.mhyusuf.hr.repository;

import com.mhyusuf.hr.dto.ReportDTO;
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

//    @Query("""
//        SELECT new com.mhyusuf.hr.dto.ReportDTO(
//            e.name,
//            p.name,
//            SUM(EXTRACT(EPOCH FROM (tr.timeTo - tr.timeFrom)) / 3600)
//        )
//        FROM TimeRecord tr
//        JOIN tr.employee e
//        JOIN tr.project p
//        WHERE tr.timeFrom BETWEEN :startDate AND :endDate
//        GROUP BY e.name, p.name
//        ORDER BY e.name, p.name
//    """)
//    List<ReportDTO> getReportData(
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate
//    );

    @Query(value = "SELECT e.name AS employeeName, p.name AS projectName, " +
            "SUM(EXTRACT(EPOCH FROM (tr.time_to - tr.time_from)) / 3600) AS totalHours " +
            "FROM time_record tr " +
            "JOIN employee e ON e.id = tr.employee_id " +
            "JOIN project p ON p.id = tr.project_id " +
            "WHERE tr.time_from BETWEEN :startDate AND :endDate " +
            "GROUP BY e.name, p.name " +
            "ORDER BY e.name, p.name",
            nativeQuery = true)
    List<Object[]> getReportRawData(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);


    default List<ReportDTO> getReportData(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> raw = getReportRawData(startDate, endDate);
        return raw.stream()
                .map(arr -> new ReportDTO(
                        (String) arr[0],
                        (String) arr[1],
                        ((Number) arr[2]).doubleValue()
                ))
                .toList();
    }
}
