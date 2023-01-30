package com.example.springjpa.controllers;

import com.example.springjpa.models.User;
import com.example.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Controller("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public String saveUser(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String email,
                         @RequestParam MultipartFile avatar,
                         @RequestParam String password,
                         Model model) {
        User user = new User(firstName, lastName, email, password);
        String fileName = userService.transferFile(avatar);
        user.setAvatar(fileName);
        User save = userService.save(user);
        model.addAttribute("user",  user);
        return "user_info";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> all = userService.getAll();
        model.addAttribute("users", all);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserByid(@PathVariable int id, Model model) {
        Optional<User> userById = userService.getUserById(id);
        model.addAttribute("user", userById.get());
        return "user_info";
    }
}
