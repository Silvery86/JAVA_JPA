package com.t2208e.T2208E_Java_JPA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private List<String> images;
    private double price;
    private int discount;
    private Long manufacturerId;
}
