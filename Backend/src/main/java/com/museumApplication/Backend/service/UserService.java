package com.museumApplication.Backend.service;

import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean verify(Users user) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
        return authentication.isAuthenticated();
    }

}
