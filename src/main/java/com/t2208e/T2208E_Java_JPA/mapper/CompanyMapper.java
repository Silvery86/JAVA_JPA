package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.CompanyDto;
import com.t2208e.T2208E_Java_JPA.dto.CorporationDto;
import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.entity.Company;
import com.t2208e.T2208E_Java_JPA.entity.Corporation;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {
    public static CompanyDto entityToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        BeanUtils.copyProperties(company, dto);
        if (company.getCorporation() != null) {
            dto.setCorporation(CorporationMapper.entityToDto(company.getCorporation()));
        }
        return dto;
    }

    public static Company dtoToEntity(CompanyDto dto) {
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        return company;
    }
}
