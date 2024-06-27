package com.t2208e.T2208E_Java_JPA.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "room_number")
    private String roomNo;
    @Column(name = "number_employee")
    private int numberEmployee;

    @OneToMany (mappedBy = "department")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
