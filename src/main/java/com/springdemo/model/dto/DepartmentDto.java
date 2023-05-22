package com.springdemo.model.dto;

import com.springdemo.model.enums.DeparmentCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private DeparmentCode departmentCode;
}
