package com.springdemo.rest;

import com.springdemo.constant.AppConstant;
import com.springdemo.model.dto.DepartmentDto;
import com.springdemo.model.dto.EditEmployeeDto;
import com.springdemo.model.dto.EmployeeDisplayDto;
import com.springdemo.model.dto.EmployeeDto;
import com.springdemo.model.entity.Department;
import com.springdemo.model.entity.Employee;
import com.springdemo.service.DepartmentService;
import com.springdemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class departmentRestAPI {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public departmentRestAPI(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employeeList")
    public ResponseEntity<Page<EmployeeDisplayDto>> showEmployee(Model model,
                                       @RequestParam (required = false, defaultValue = AppConstant.DEFAULT_PAGE) Integer page,
                                       @RequestParam (required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) Integer size,
                                       @RequestParam (required = false, name = "sort",
                       defaultValue = AppConstant.DEFAULT_SORT_FIELD) List<String>sorts,
                                       @RequestParam (required = false, defaultValue = "", name = "q") Optional<String> keyword){

        List<Sort.Order> orderList = new ArrayList<>();
        for(String sort : sorts){
           boolean isAsc = sort.startsWith("-");
              orderList.add(isAsc ? Sort.Order.asc(sort.substring(1)) : Sort.Order.desc(sort));
        }
        Specification<Employee> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), false);

        if(keyword.isPresent() && !keyword.get().trim().isEmpty()){
        Specification<Employee> specKeyword = (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + keyword.get() + "%"),
                        criteriaBuilder.like(root.get("phone"), "%" + keyword.get() + "%"));
            spec = spec.and(specKeyword);
        }

        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(orderList));
        Page<Employee> employeePage = employeeService.findEmployeePaging(spec, pageRequest) ;

        List<EmployeeDisplayDto> employeeDisplayDtoList = employeePage.getContent().stream().map(employee -> {
            EmployeeDisplayDto employeeDisplayDto = new EmployeeDisplayDto();
            BeanUtils.copyProperties(employee, employeeDisplayDto);
            return employeeDisplayDto;
        }).collect(Collectors.toList());
        Page<EmployeeDisplayDto> employeeDisplayDtoPage = new PageImpl<>(employeeDisplayDtoList, pageRequest, employeePage.getTotalElements());

        return ResponseEntity.ok(employeeDisplayDtoPage);
    }





}
