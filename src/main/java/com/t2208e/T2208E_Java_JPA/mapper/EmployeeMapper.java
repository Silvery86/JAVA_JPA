package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.EmployeeDto;
import com.t2208e.T2208E_Java_JPA.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    public static EmployeeDto entityToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    public static Employee dtoToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }
}
