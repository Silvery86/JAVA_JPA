package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.CompanyDto;
import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.Company;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import com.t2208e.T2208E_Java_JPA.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDto entityToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        BeanUtils.copyProperties(department, dto);
        dto.setNumberEmployee(department.getUsers().size());
        if (department.getCompany() != null) {
            dto.setCompany(CompanyMapper.entityToDto(department.getCompany()));
        }
        return dto;
    }

    public static Department dtoToEntity(DepartmentDto dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        return department;
    }
}
