package com.springdemo.service.departmentServiceImpl;

import com.springdemo.model.entity.Employee;
import com.springdemo.repository.EmployeeRepository;
import com.springdemo.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findEmployeePaging(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findEmployeePaging(Specification<Employee> spec, Pageable pageable) {
        return employeeRepository.findAll(spec, pageable);
    }

    @Override
    public Employee findByNameAndDeletedFalse(String name) {
        return employeeRepository.findByNameAndDeletedFalse(name);
    }

    @Override
    public void save(Employee employee) {
        employee.setDeleted(false);
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }

    @Override
    public boolean existsByUsernameAndDeletedFalse(String name) {
        Objects.requireNonNull(name);
        return employeeRepository.existsByUsernameAndDeletedFalse(name);
    }

    @Override
    public boolean existsByEmailAndDeletedFalse(String email) {
        Objects.requireNonNull(email);
        return employeeRepository.existsByEmailAndDeletedFalse(email);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }


}
