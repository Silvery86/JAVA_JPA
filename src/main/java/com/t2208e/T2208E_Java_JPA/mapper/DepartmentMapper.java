package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import com.t2208e.T2208E_Java_JPA.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDto entityToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        BeanUtils.copyProperties(department, dto);
        List<User> users = department.getUsers();
        dto.setUsersDto(users.stream().map(UserMapper::entityToDto).collect(Collectors.toList()));
        if (department.getCompany() != null) {
            dto.setCompanyId(department.getCompany().getId());
        }
        return dto;
    }
}
