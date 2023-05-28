package com.springdemo.model.entity;

import com.springdemo.model.enums.EmployeeLevel;
import com.springdemo.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Nationalized
    private String name;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    @Nationalized
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
    @Enumerated(EnumType.STRING)
    private EmployeeLevel level;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;

}
