package com.hrms.service;

import com.hrms.model.EmployeeFile;
import com.hrms.model.UserAccount;
import com.hrms.repository.EmployeeFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeFileService {
    private final EmployeeFileRepository repository;

    public EmployeeFileService(EmployeeFileRepository repository) {
        this.repository = repository;
    }

    public EmployeeFile save(EmployeeFile file) {
        return repository.save(file);
    }

    public List<EmployeeFile> listAll() {
        return repository.findAll();
    }

    public Optional<EmployeeFile> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<EmployeeFile> findByUser(UserAccount user) {
        return repository.findByUser(user);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(file -> {
            file.setDeleted(true);
            repository.save(file);
        });
    }

    public void restore(Long id) {
        repository.findById(id).ifPresent(file -> {
            file.setDeleted(false);
            repository.save(file);
        });
    }
}
