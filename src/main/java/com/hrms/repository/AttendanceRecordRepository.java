package com.hrms.repository;

import com.hrms.model.AttendanceRecord;
import com.hrms.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByUser(UserAccount user);
}
