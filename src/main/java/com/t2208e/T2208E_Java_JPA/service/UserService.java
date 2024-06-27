package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.PageDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.User;

public interface UserService {
    User findById(long id);
    PageDto search();
    PageDto search(UserDto dto);
}
