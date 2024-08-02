package com.t2208e.T2208E_Java_JPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    private String type;
    private String description;
    private List<String> images;
    private double price;
    private int discount;

    @OneToMany(mappedBy = "manufacturer_id",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Manufacturer manufacturer;

}
