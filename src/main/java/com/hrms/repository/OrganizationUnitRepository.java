package com.hrms.repository;

import com.hrms.model.OrganizationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, Long> {
    List<OrganizationUnit> findByLevel(int level);
}
