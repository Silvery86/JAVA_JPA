package com.t2208e.T2208E_Java_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "number_department")
    private int numberDepartment;

    @OneToMany(mappedBy = "company")
    private List<Department> departments;

    @ManyToOne
    @JoinColumn(name = "corporation_id")
    private Corporation corporation;

}
