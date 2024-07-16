package com.t2208e.T2208E_Java_JPA.service.impl;


import com.t2208e.T2208E_Java_JPA.config.properties.CommonProperties;
import com.t2208e.T2208E_Java_JPA.dto.PageDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.Company;
import com.t2208e.T2208E_Java_JPA.entity.Corporation;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import com.t2208e.T2208E_Java_JPA.entity.User;
import com.t2208e.T2208E_Java_JPA.exception.ResourceNotFoundException;
import com.t2208e.T2208E_Java_JPA.mapper.UserMapper;
import com.t2208e.T2208E_Java_JPA.repository.CompanyRepository;
import com.t2208e.T2208E_Java_JPA.repository.CorporationRepository;
import com.t2208e.T2208E_Java_JPA.repository.DepartmentRepository;
import com.t2208e.T2208E_Java_JPA.repository.UserRepository;
import com.t2208e.T2208E_Java_JPA.service.UserService;
import com.t2208e.T2208E_Java_JPA.specification.UserSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSpecification userSpecification;
    @Autowired
    private CommonProperties commonProperties;
    @Autowired
    private DepartmentRepository departmentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageDto search() {
        UserDto dto = new UserDto();
        dto.setPageNumber(commonProperties.getDefaultPageNumber());
        dto.setPageSize(commonProperties.getDefaultPageSize());
        return search(dto);
    }

    @Override
    public PageDto search(UserDto dto) {
        if (dto.getPageSize() <= 0 || dto.getPageNumber() < 0) {
            dto.setPageNumber(commonProperties.getDefaultPageNumber());
            dto.setPageSize(commonProperties.getDefaultPageSize());
        }
        Page<User> page = userRepository.findAll(userSpecification.filter(dto),
                PageRequest.of(dto.getPageNumber(), dto.getPageSize(),
                        Sort.by("id").ascending()));
        PageDto pageDto = new PageDto();
        pageDto.setContent(page.getContent()
                .stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList()));
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setNumber(page.getNumber());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;
    }

    @Override
    public List<UserDto> searchUsers(String userName, String address) {
        List<User> users = userRepository.findByUserNameAndAddress(userName, address);
        return users.stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        User userToUpdate = userRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // Update basic user details
        userToUpdate.setUserName(dto.getUserName());
        userToUpdate.setFirstName(dto.getFirstName());
        userToUpdate.setLastName(dto.getLastName());
        userToUpdate.setAddress(dto.getAddress());
        userToUpdate.setUpdatedBy(dto.getUpdatedBy());
        userToUpdate.setUpdatedTime(new Date());
        // Update department details if provided
        if (dto.getDepartment() != null && dto.getDepartment().getId() != null) {
            userToUpdate.setDepartment(departmentRepository.findById(dto.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found")));
        }
        // Save and return updated user entity
        User updatedUser = userRepository.save(userToUpdate);
        return UserMapper.entityToDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(userToDelete);
    }

    @Override
    public UserDto getUserByIdWithDetails(long id) {
        return userRepository.findByIdWithDetails(id)
                .map(UserMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
