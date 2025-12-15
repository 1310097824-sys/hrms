package com.hrms.service;

import com.hrms.model.PayrollRecord;
import com.hrms.model.UserAccount;
import com.hrms.repository.PayrollRecordRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class PayrollService {
    private final PayrollRecordRepository repository;

    public PayrollService(PayrollRecordRepository repository) {
        this.repository = repository;
    }

    public PayrollRecord save(PayrollRecord record) {
        return repository.save(record);
    }

    public List<PayrollRecord> listAll() {
        return repository.findAll();
    }

    public List<PayrollRecord> listByUser(UserAccount user) {
        return repository.findByUser(user);
    }

    public List<PayrollRecord> listByUserAndMonth(UserAccount user, YearMonth month) {
        return repository.findByUserAndMonth(user, month);
    }
}
