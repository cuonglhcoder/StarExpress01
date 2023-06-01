package com.springdemo.model.dto;

import com.springdemo.model.enums.EmployeeLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.time.LocalDateTime;
@Getter
@Setter
public class EmployeeDisplayDto {
    private Long id;
    @Nationalized
    private String name;
    private String username;
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
