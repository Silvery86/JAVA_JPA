package com.t2208e.T2208E_Java_JPA.service.impl;

import com.t2208e.T2208E_Java_JPA.dto.ManufacturerDTO;
import com.t2208e.T2208E_Java_JPA.entity.Manufacturer;
import com.t2208e.T2208E_Java_JPA.repository.ManufacturerRepository;
import com.t2208e.T2208E_Java_JPA.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<ManufacturerDTO> getAllManufacturers() {
        return manufacturerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ManufacturerDTO getManufacturerById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer.map(this::toDTO).orElse(null);
    }

    @Override
    public ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = toEntity(manufacturerDTO);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return toDTO(savedManufacturer);
    }

    @Override
    public ManufacturerDTO updateManufacturer(Long id, ManufacturerDTO manufacturerDTO) {
        Manufacturer existingManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id: " + id));
        existingManufacturer.setName(manufacturerDTO.getName());
        existingManufacturer.setOrigin(manufacturerDTO.getOrigin());
        existingManufacturer.setDescription(manufacturerDTO.getDescription());
        Manufacturer updatedManufacturer = manufacturerRepository.save(existingManufacturer);
        return toDTO(updatedManufacturer);
    }


    @Override
    public void deleteManufacturer(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
        if (manufacturer != null && (manufacturer.getProducts() == null || manufacturer.getProducts().isEmpty())) {
            manufacturerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete manufacturer with products.");
        }
    }

    private ManufacturerDTO toDTO(Manufacturer manufacturer) {
        return new ManufacturerDTO(
                manufacturer.getId(),
                manufacturer.getName(),
                manufacturer.getOrigin(),
                manufacturer.getDescription()
        );
    }

    private Manufacturer toEntity(ManufacturerDTO dto) {
        return new Manufacturer(
                dto.getId(),
                dto.getName(),
                dto.getOrigin(),
                dto.getDescription(),
                null
        );
    }
}
