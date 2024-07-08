package com.t2208e.T2208E_Java_JPA.service.impl;

import com.t2208e.T2208E_Java_JPA.dto.EmployeeDto;
import com.t2208e.T2208E_Java_JPA.entity.Employee;
import com.t2208e.T2208E_Java_JPA.mapper.EmployeeMapper;
import com.t2208e.T2208E_Java_JPA.repository.EmployeeRepository;
import com.t2208e.T2208E_Java_JPA.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.dtoToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.entityToDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
