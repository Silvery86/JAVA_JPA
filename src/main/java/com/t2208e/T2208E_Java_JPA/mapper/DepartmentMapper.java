package com.t2208e.T2208E_Java_JPA.mapper;

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

        // Map users
        List<UserDto> usersDto = department.getUsers().stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList());
        dto.setUsersDto(usersDto);

        // Optionally map company ID
        if (department.getCompany() != null) {
            dto.setCompany(CompanyMapper.entityToDto(department.getCompany()));
        }

        return dto;
    }

    public static Department dtoToEntity(DepartmentDto dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);

        // Set users
        List<User> users = dto.getUsersDto().stream()
                .map(UserMapper::dtoToEntity)
                .collect(Collectors.toList());
        department.setUsers(users);

        // Optionally set company entity if company ID is provided
        if (dto.getCompany() != null && dto.getCompany().getId() != null) {
            Company company = new Company();
            company.setId(dto.getCompany().getId());
            // Optionally map other company properties

            department.setCompany(company);
        }

        return department;
    }
}
