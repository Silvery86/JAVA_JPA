package com.t2208e.T2208E_Java_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "corporation")
public class Corporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "corporation_name")
    private String corporationName;
    @Column(name = "number_company")
    private int numberCompany;

    @OneToMany(mappedBy = "corporation")
    private List<Company> companies;
}
