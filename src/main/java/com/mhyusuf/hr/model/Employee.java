package com.mhyusuf.hr.model;

import com.mhyusuf.setting.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employee")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Tipe data BIGINT

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    // Relasi One-to-Many ke TimeRecord
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimeRecord> timeRecords;
}