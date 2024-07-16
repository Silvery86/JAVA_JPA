package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.CorporationDto;

import java.util.List;

public interface CorporationService {
    List<CorporationDto> getAllCorporationsWithDetails();
}
