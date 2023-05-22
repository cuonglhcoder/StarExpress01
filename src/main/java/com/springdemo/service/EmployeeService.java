package com.springdemo.service;

import com.springdemo.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAllEmployee();
    public Employee findByNameAndDeletedFalse(String departmentName);
    public void save(Employee employee);
}
