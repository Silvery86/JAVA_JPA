package com.t2208e.T2208E_Java_JPA.controller;

import com.t2208e.T2208E_Java_JPA.dto.ManufacturerDTO;
import com.t2208e.T2208E_Java_JPA.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        return ResponseEntity.ok(manufacturerService.getAllManufacturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturerService.getManufacturerById(id));
    }

    @PostMapping
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.createManufacturer(manufacturerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(@PathVariable Long id, @RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(id, manufacturerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManufacturer(@PathVariable Long id) {
        try {
            manufacturerService.deleteManufacturer(id);
            return ResponseEntity.ok("Manufacturer with ID " + id + " was successfully deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Manufacturer with ID " + id + " not found or have products in it.");
        }
    }
}
