package com.springdemo.model.entity;

import com.springdemo.model.enums.DeparmentCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Setter
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long departmentId;

    private String departmentName;
    @Enumerated(EnumType.STRING)
    private DeparmentCode departmentCode;
    private LocalDateTime createdDate;
    private Boolean deleted;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employee;
}
