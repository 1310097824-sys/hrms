package com.hrms.repository;

import com.hrms.model.EmployeeFile;
import com.hrms.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, Long> {
    Optional<EmployeeFile> findByUser(UserAccount user);
}
