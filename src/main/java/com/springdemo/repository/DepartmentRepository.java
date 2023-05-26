package com.springdemo.repository;

import com.springdemo.model.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long> {
    Department findByDepartmentNameAndDeletedFalse(String name);

    Department getDepartmentByDepartmentNameAndDeletedFalse(String name);
}
