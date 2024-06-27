package com.t2208e.T2208E_Java_JPA.repository;

import com.t2208e.T2208E_Java_JPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.userName LIKE %:userName% AND u.address LIKE %:address%")
    List<User> findByUserNameAndAddress(@Param(value = "userName") String userName, @Param(value = "address") String address);
}
