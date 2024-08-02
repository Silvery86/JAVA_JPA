package com.t2208e.T2208E_Java_JPA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {
    private Long id;
    private String name;
    private String origin;
    private String description;
}
