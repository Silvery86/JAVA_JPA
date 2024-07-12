package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import com.t2208e.T2208E_Java_JPA.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static UserDto entityToDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);

        if (user.getDepartment() != null) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(user.getDepartment().getId());
            departmentDto.setDepartmentName(user.getDepartment().getDepartmentName());
            departmentDto.setRoomNo(user.getDepartment().getRoomNo());
            departmentDto.setNumberEmployee(user.getDepartment().getNumberEmployee());
            dto.setDepartment(departmentDto);
        }

        return dto;
    }

    public static User dtoToEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        // Set department entity if departmentId is provided
        if (dto.getDepartment() != null && dto.getDepartment().getId() != null) {
            Department department = new Department();
            department.setId(dto.getDepartment().getId());
            user.setDepartment(department);
        }

        return user;
    }
}
