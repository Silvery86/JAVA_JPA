package com.t2208e.T2208E_Java_JPA.service.impl;

import com.t2208e.T2208E_Java_JPA.dto.CorporationDto;
import com.t2208e.T2208E_Java_JPA.dto.CorporationProjection;
import com.t2208e.T2208E_Java_JPA.repository.CorporationRepository;
import com.t2208e.T2208E_Java_JPA.service.CorporationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporationServiceImpl implements CorporationService {

    @Autowired
    private CorporationRepository corporationRepository;

    @Override
    public List<CorporationDto> getAllCorporationsWithDetails() {
        List<CorporationProjection> projections = corporationRepository.findAllWithDetails();
        return projections.stream().map(projection -> {
            CorporationDto dto = new CorporationDto();
            BeanUtils.copyProperties(projection, dto);
            dto.setNumberCompany(projection.getNumberCompany());
            dto.setNumberDepartment(projection.getNumberDepartment());
            dto.setNumberEmployee(projection.getNumberEmployee());
            return dto;
        }).collect(Collectors.toList());
    }
}
