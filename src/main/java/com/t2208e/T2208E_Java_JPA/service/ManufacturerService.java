package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerDTO> getAllManufacturers();
    ManufacturerDTO getManufacturerById(Long id);
    ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO);
    ManufacturerDTO updateManufacturer(Long id, ManufacturerDTO manufacturerDTO);
    void deleteManufacturer(Long id);
}
