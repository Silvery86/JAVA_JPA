package com.t2208e.T2208E_Java_JPA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String companyName;
    private int numberDepartment;
    private Long corporationId;
    private List<DepartmentDto> departmentsDto;
}
