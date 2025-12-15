package com.hrms.service;

import com.hrms.model.AttendanceRecord;
import com.hrms.model.UserAccount;
import com.hrms.repository.AttendanceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRecordRepository repository;

    public AttendanceService(AttendanceRecordRepository repository) {
        this.repository = repository;
    }

    public AttendanceRecord checkIn(UserAccount user) {
        AttendanceRecord record = new AttendanceRecord();
        record.setUser(user);
        record.setCheckInTime(LocalDateTime.now());
        return repository.save(record);
    }

    public List<AttendanceRecord> history(UserAccount user) {
        return repository.findByUser(user);
    }
}
