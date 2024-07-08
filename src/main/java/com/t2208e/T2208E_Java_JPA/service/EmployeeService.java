package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> findAllEmployees();
}
