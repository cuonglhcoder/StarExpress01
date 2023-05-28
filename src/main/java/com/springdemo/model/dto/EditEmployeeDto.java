package com.springdemo.model.dto;

import com.springdemo.model.enums.EmployeeLevel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Getter
@Setter
public class EditEmployeeDto {
    private Long id;
    @Nationalized
    @NotBlank(message = "{employee.error.nulls}")
    private String name;
    @NotBlank(message = "{employee.error.nulls}")
    private String username;
    @NotBlank(message = "{employee.error.nulls}")
    @Column(unique = true)
     private String email;
    @NotBlank(message = "{employee.error.nulls}")
    private String password;
    @NotBlank(message = "{employee.error.nulls}")
    private String phone;
    @Nationalized
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EmployeeLevel level;
    private String departmentName;
}
