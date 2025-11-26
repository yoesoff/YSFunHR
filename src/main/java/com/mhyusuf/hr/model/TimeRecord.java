package com.mhyusuf.hr.model;

import com.mhyusuf.setting.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "time_record")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Tipe data BIGINT

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Foreign Key ke Employee

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Foreign Key ke Project

    @Column(name = "time_from", nullable = false)
    private LocalDateTime timeFrom;

    @Column(name = "time_to", nullable = false)
    private LocalDateTime timeTo;
}