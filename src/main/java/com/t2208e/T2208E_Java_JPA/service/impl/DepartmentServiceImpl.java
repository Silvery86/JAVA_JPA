package com.t2208e.T2208E_Java_JPA.service.impl;

import com.t2208e.T2208E_Java_JPA.dto.DepartmentDto;
import com.t2208e.T2208E_Java_JPA.entity.Department;
import com.t2208e.T2208E_Java_JPA.mapper.DepartmentMapper;
import com.t2208e.T2208E_Java_JPA.repository.DepartmentRepository;
import com.t2208e.T2208E_Java_JPA.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        DepartmentDto departmentDto = DepartmentMapper.entityToDto(department);
        departmentDto.setNumberEmployee(department.getUsers().size()); // Ensure the value is set correctly
        return departmentDto;
    }
}
