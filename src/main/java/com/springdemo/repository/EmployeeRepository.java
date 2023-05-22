package com.springdemo.repository;

import com.springdemo.model.entity.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long>{
    Employee findByNameAndDeletedFalse(String name);
}
