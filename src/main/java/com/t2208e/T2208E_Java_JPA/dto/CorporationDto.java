package com.t2208e.T2208E_Java_JPA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CorporationDto {
    private Long id;
    private String corporationName;
    private int numberCompany;
    private int numberDepartment;
    private int numberEmployee;
    private List<CompanyDto> companiesDto;
}
