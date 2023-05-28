package com.springdemo.repository;

import com.springdemo.model.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>{
    Employee findByNameAndDeletedFalse(String name);
    boolean existsByUsernameAndDeletedFalse(String name);
    boolean existsByEmailAndDeletedFalse(String email);
//    @Query("select e from Employee e where (e.deleted = false)")
//    List<Employee> findAll();
}
