package com.springdemo.service.departmentServiceImpl;

import com.springdemo.model.entity.Department;
import com.springdemo.repository.DepartmentRepository;
import com.springdemo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImp(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public void save(Department department) {
        department.setCreatedDate(LocalDateTime.now());
        department.setDeleted(false);
        departmentRepository.save(department);
    }

    @Override
    public Department findByNameAndDeletedFalse(String departmentName) {
        return departmentRepository.findByNameAndDeletedFalse(departmentName);
    }
}
