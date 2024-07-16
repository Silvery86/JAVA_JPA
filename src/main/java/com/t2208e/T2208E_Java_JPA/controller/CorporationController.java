package com.t2208e.T2208E_Java_JPA.controller;

import com.t2208e.T2208E_Java_JPA.dto.CorporationDto;
import com.t2208e.T2208E_Java_JPA.service.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/corporations")
public class CorporationController {
    @Autowired
    private CorporationService corporationService;

    @GetMapping()
    public List<CorporationDto> getAllCorporationsWithDetails() {
        return corporationService.getAllCorporationsWithDetails();
    }
}
