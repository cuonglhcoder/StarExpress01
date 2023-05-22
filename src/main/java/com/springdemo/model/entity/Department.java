package com.springdemo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
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
    private String departmentCode;
    private LocalDateTime createdDate;
    private Boolean deleted;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employee;
}
