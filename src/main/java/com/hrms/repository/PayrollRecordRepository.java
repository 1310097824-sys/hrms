package com.hrms.repository;

import com.hrms.model.PayrollRecord;
import com.hrms.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Long> {
    List<PayrollRecord> findByUser(UserAccount user);
    List<PayrollRecord> findByUserAndMonth(UserAccount user, YearMonth month);
}
