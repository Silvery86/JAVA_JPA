package com.t2208e.T2208E_Java_JPA.repository;

import com.t2208e.T2208E_Java_JPA.dto.CorporationProjection;
import com.t2208e.T2208E_Java_JPA.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorporationRepository extends JpaRepository<Corporation, Long> {
    @Query(value = "SELECT c.id AS id, c.corporation_name AS corporationName, " +
            "COUNT(DISTINCT co.id) AS numberCompany, " +
            "COUNT(DISTINCT d.id) AS numberDepartment, " +
            "COUNT(DISTINCT u.id) AS numberEmployee " +
            "FROM corporation c " +
            "LEFT JOIN company co ON c.id = co.corporation_id " +
            "LEFT JOIN department d ON co.id = d.company_id " +
            "LEFT JOIN user u ON d.id = u.department_id " +
            "GROUP BY c.id", nativeQuery = true)
    List<CorporationProjection> findAllWithDetails();
}
