package com.springdemo.controller;

import com.springdemo.model.dto.DepartmentDto;
import com.springdemo.model.dto.EmployeeDto;
import com.springdemo.model.entity.Department;
import com.springdemo.model.entity.Employee;
import com.springdemo.service.DepartmentService;
import com.springdemo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class departmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public departmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("Department/createNewDepart")
    public String showFormCreateNewDepart(Model model){
        model.addAttribute("departmentDto", new DepartmentDto());
        return "hrMng/createNewDept";
    }
    @PostMapping("Department/createNewDepart")
    public String CreateNewDepart(DepartmentDto departmentDto){
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        departmentService.save(department);
        return "hrMng/createNewDept";
    }
    @GetMapping("/departmentMng")
    public String showDepart(Model model){
        List<Department> departmentlist = departmentService.findAll();
        model.addAttribute("departmentList", departmentlist);
        return "hrMng/departmentMng";
    }

    @GetMapping("/loginForm")
    public String showLoginForm(){
        return "hrMng/loginForm";
    }


    @GetMapping("/employeeList")
    public String showEmployee(Model model){
        List<Employee> employeeList = employeeService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);
        return "hrMng/employeeList";
    }

    @GetMapping("Employee/EmployeeList")
    public String showAddNewEmployeeForm(Model model){
        List<String> departmentNameList = new ArrayList<>();
        List<Department> departmentList = departmentService.findAll();
        for (Department department : departmentList){
            departmentNameList.add(department.getDepartmentName());
        }
        model.addAttribute("employeeDto", new EmployeeDto());
        model.addAttribute("departmentNameList", departmentNameList);
        return "hrMng/createNewEmployee";
    }

    @PostMapping("Employee/AddEmployee")
    public String addNewEmployee(EmployeeDto employeeDto){

        String departmentName = employeeDto.getDepartmentName();
        Department department = departmentService.findByNameAndDeletedFalse(departmentName);

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employee.setDepartment(department);

        employeeService.save(employee);
        return "hrMng/employeeList";
    }
}
