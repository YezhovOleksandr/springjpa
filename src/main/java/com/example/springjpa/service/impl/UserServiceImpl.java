package com.example.springjpa.service.impl;

import com.example.springjpa.models.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.UserService;
import com.example.springjpa.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> getUserById(int id) {
        Optional<User> userFoundById = userRepository.findById(id);
        if (userFoundById.isPresent()) {
            return Optional.of(userFoundById.get());
        } else {
            throw new RuntimeException("User not found");

        }
    }

    @Override
    public List<User> getAll() {
       return userRepository.findAll();
    }

    @Override
    public User save(User newUser) {
        Optional<User> byEmail = userRepository.findByEmail(newUser.getEmail());
        if (byEmail.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(int id) {
       Optional<User> user = getUserById(id);
        userRepository.delete(Objects.requireNonNull(user.orElse(null)));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String transferFile(MultipartFile avatar) {
        String pathToFolder = FileUtils.getImagesFolderPath();
        System.out.println("PATH - " + avatar.getOriginalFilename());

        try {
            avatar.transferTo(new File(pathToFolder + avatar.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return avatar.getOriginalFilename();
    }
}
