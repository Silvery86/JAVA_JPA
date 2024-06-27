package com.t2208e.T2208E_Java_JPA.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;

    // common field
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_time")
    private Date updatedTime;

    @PrePersist
    public void prePersist(){
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }
    @PreUpdate
    public void preUpdate(){
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
