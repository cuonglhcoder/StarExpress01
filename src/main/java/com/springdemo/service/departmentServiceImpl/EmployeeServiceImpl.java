package com.springdemo.service.departmentServiceImpl;

import com.springdemo.model.entity.Employee;
import com.springdemo.repository.EmployeeRepository;
import com.springdemo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Employee findByNameAndDeletedFalse(String name) {
        return employeeRepository.findByNameAndDeletedFalse(name);
    }

    @Override
    public void save(Employee employee) {
        employee.setDeleted(false);
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }
}
