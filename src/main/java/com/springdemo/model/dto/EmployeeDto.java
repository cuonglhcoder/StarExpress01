package com.springdemo.model.dto;

import com.springdemo.model.entity.Department;
import com.springdemo.model.enums.EmployeeLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.time.LocalDateTime;
@Getter
@Setter
public class EmployeeDto {
    private Long id;
    @Nationalized
    private String name;
     private String email;
    private String password;
    private String phone;
    @Nationalized
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EmployeeLevel level;
    private String departmentName;
}
