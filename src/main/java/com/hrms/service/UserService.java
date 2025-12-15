package com.hrms.service;

import com.hrms.model.Position;
import com.hrms.model.UserAccount;
import com.hrms.model.UserRole;
import com.hrms.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserAccountRepository repository;

    public UserService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<UserAccount> login(String username, String password) {
        return repository.findByUsername(username)
                .filter(user -> user.isActive() && user.getPassword().equals(password));
    }

    public List<UserAccount> listAll() {
        return repository.findAll();
    }

    public UserAccount save(UserAccount user, Position position, UserRole role) {
        user.setPosition(position);
        user.setRole(role);
        return repository.save(user);
    }

    public Optional<UserAccount> findById(Long id) {
        return repository.findById(id);
    }
}
