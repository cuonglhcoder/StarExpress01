package com.springdemo.controller;

import com.springdemo.constant.AppConstant;
import com.springdemo.model.dto.DepartmentDto;
import com.springdemo.model.dto.EditEmployeeDto;
import com.springdemo.model.dto.EmployeeDto;
import com.springdemo.model.entity.Department;
import com.springdemo.model.entity.Employee;
import com.springdemo.service.DepartmentService;
import com.springdemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/Department/createNewDepart")
    public String showFormCreateNewDepart(Model model){
        model.addAttribute("departmentDto", new DepartmentDto());
        return "hrMng/createNewDept";
    }
    @PostMapping("Department/createNewDepart")
    public String CreateNewDepart(@Valid DepartmentDto departmentDto,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "hrMng/createNewDept";
        }
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
    public String showEmployee(Model model,
                               @RequestParam (required = false, defaultValue = AppConstant.DEFAULT_PAGE) Integer page,
                               @RequestParam (required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) Integer size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<Employee> employeePage = employeeService.findEmployeePaging(pageRequest) ;
        model.addAttribute("employeePage", employeePage);
        return "hrMng/employeeList";
    }

    @GetMapping("Employee/AddEmployee")
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

    @PostMapping({"Employee/AddEmployee"})
    public String addNewEmployee(@Valid EmployeeDto employeeDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "hrMng/createNewEmployee";
        }
        if (employeeService.existsByUsernameAndDeletedFalse(employeeDto.getUsername())){
            bindingResult.rejectValue("username", "error.username.duplicate");
        }
        if (employeeService.existsByEmailAndDeletedFalse(employeeDto.getEmail())){
            bindingResult.rejectValue("email", "error.email.duplicate");
        }
        if (bindingResult.hasErrors()){
            return "hrMng/createNewEmployee";
        }

        String departmentName = employeeDto.getDepartmentName();
        Department department = departmentService.findByDepartmentNameAndDeletedFalse(departmentName);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employee.setDepartment(department);
        employeeService.save(employee);

        redirectAttributes.addFlashAttribute("successMessage", "employee.create.success");
        return "redirect:/employeeList";
    }

    @GetMapping("Employee/editEmployee/{id}")
    public String showEditEmployeeForm(Model model, @PathVariable Long id){
        Optional<Employee> employeeOptional = employeeService.findById(id);
        System.out.println("id = " + id);
        if (employeeOptional.isEmpty()){
            return "error/404";
        }
        Employee employee = employeeOptional.get();
        EditEmployeeDto editEmployeeDto = new EditEmployeeDto();
        BeanUtils.copyProperties(employee, editEmployeeDto);
        model.addAttribute("editEmployeeDto", editEmployeeDto);
        return "hrMng/editEmployee";
    }
    @PostMapping("/employee/update-employee")
    public String updateEmployeeForm(EditEmployeeDto editEmployeeDto){
        Department department = departmentService.findByDepartmentNameAndDeletedFalse(editEmployeeDto.getDepartmentName());
        Employee employee = new Employee();
        BeanUtils.copyProperties(editEmployeeDto, employee);
        employee.setDepartment(department);
        employeeService.save(employee);
        return "redirect:/employeeList";
    }
}
