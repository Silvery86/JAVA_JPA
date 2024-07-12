package com.t2208e.T2208E_Java_JPA.mapper;

import com.t2208e.T2208E_Java_JPA.dto.CompanyDto;
import com.t2208e.T2208E_Java_JPA.dto.CorporationDto;
import com.t2208e.T2208E_Java_JPA.entity.Company;
import com.t2208e.T2208E_Java_JPA.entity.Corporation;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CorporationMapper {
    public static CorporationDto entityToDto(Corporation corporation) {
        CorporationDto dto = new CorporationDto();
        BeanUtils.copyProperties(corporation, dto);

        // Map companies
        List<CompanyDto> companiesDto = corporation.getCompanies().stream()
                .map(CompanyMapper::entityToDto)
                .collect(Collectors.toList());
        dto.setCompaniesDto(companiesDto);

        return dto;
    }

    public static Corporation dtoToEntity(CorporationDto dto) {
        Corporation corporation = new Corporation();
        BeanUtils.copyProperties(dto, corporation);

        // Set companies
        List<Company> companies = dto.getCompaniesDto().stream()
                .map(CompanyMapper::dtoToEntity)
                .collect(Collectors.toList());
        corporation.setCompanies(companies);

        return corporation;
    }
}
