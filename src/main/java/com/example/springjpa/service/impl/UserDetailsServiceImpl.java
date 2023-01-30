package com.example.springjpa.service.impl;

import com.example.springjpa.models.User;
import com.example.springjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> email = userRepository.findByEmail(username);
        if (email.isPresent()) {
            return email.get();
        }
        throw new UsernameNotFoundException("User with" + username + "not found");
    }
}
