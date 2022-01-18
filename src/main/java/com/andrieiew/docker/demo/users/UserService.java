package com.andrieiew.docker.demo.users;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findById(username);
    }

    public User save(User entity) {
        return userRepository.save(entity);
    }

    public void delete(User entity) {
        userRepository.delete(entity);
    }

    public void clear() {
        userRepository.deleteAll();
    }
}
