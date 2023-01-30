package com.example.springjpa.service;

import com.example.springjpa.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(int id);

    List<User> getAll();

    User save(User save);

    void deleteUser(int id);

    User updateUser(User user);

    String transferFile(MultipartFile avatar);
}
