package com.t2208e.T2208E_Java_JPA.specification;


import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public Specification<User> filter(UserDto criteria){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!StringUtils.isEmpty(criteria.getUserName())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("userName")),
                        "%"+ criteria.getUserName().toUpperCase() + "%"));
            }
            if(!StringUtils.isEmpty(criteria.getFirstName())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("firstname")),
                        "%"+ criteria.getFirstName().toUpperCase() + "%"));
            }
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
