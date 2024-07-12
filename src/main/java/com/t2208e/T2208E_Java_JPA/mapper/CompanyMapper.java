package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.CompanyDto;
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

        // Map departments
        List<DepartmentDto> departmentsDto = company.getDepartments().stream()
                .map(DepartmentMapper::entityToDto)
                .collect(Collectors.toList());
        dto.setDepartmentsDto(departmentsDto);

        // Optionally map corporation ID
        if (company.getCorporation() != null) {
            dto.setCorporationId(company.getCorporation().getId());
        }

        return dto;
    }

    public static Company dtoToEntity(CompanyDto dto) {
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);

        // Set departments
        List<Department> departments = dto.getDepartmentsDto().stream()
                .map(DepartmentMapper::dtoToEntity)
                .collect(Collectors.toList());
        company.setDepartments(departments);

        // Optionally set corporation entity if corporation ID is provided
        if (dto.getCorporationId() != null) {
            Corporation corporation = new Corporation();
            corporation.setId(dto.getCorporationId());
            // Optionally map other corporation properties

            company.setCorporation(corporation);
        }

        return company;
    }
}
