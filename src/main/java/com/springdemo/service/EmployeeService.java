package com.springdemo.service;

import com.springdemo.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> findAllEmployee();
    public Page<Employee> findEmployeePaging(Pageable pageable);
    public Employee findByNameAndDeletedFalse(String departmentName);
    public void save(Employee employee);
    boolean existsByUsernameAndDeletedFalse(String name);
    boolean existsByEmailAndDeletedFalse(String email);
    public Optional<Employee> findById(Long id);
}
