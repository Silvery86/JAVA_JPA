package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.PageDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(long id);
    PageDto search();
    PageDto search(UserDto dto);
    List<UserDto> searchUsers(String userName, String address);
    UserDto updateUser(UserDto userDto);
    void deleteUserById(Long userId);
    UserDto getUserByIdWithDetails(long id);
}
