package com.t2208e.T2208E_Java_JPA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto extends PadingDto{
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private Long departmentId;
}
