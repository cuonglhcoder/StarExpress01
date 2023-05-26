package com.springdemo.service;

import com.springdemo.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    public void save(Department department);
    public Department findByDepartmentNameAndDeletedFalse(String name);
}
