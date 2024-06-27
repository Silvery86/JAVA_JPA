package com.t2208e.T2208E_Java_JPA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private String roomNo;
    private int numberEmployee;
    private Long companyId;
    private List<UserDto> usersDto;
}
