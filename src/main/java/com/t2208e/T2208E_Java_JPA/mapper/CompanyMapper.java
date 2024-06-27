package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.CompanyDto;
import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.entity.Company;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class CompanyMapper {
    public static CompanyDto entityToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        BeanUtils.copyProperties(company, dto);
        if (company.getDepartments() != null) {
            dto.setDepartmentsDto(company.getDepartments().stream()
                    .map(DepartmentMapper::entityToDto)
                    .collect(Collectors.toList()));
        }
        if (company.getCorporation() != null) {
            dto.setCorporationId(company.getCorporation().getId());
        }
        return dto;
    }

    public static Company dtoToEntity(CompanyDto dto) {
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        return company;
    }
}
