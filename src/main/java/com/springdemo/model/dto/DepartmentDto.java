package com.springdemo.model.dto;

import com.springdemo.model.enums.DeparmentCode;
import com.springdemo.model.enums.DeptCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDto {
    private DeptCategory deptCategory;
    @NotBlank(message = "{department.error.nulls}")
    private String departmentName;
    private DeparmentCode departmentCode;

}
