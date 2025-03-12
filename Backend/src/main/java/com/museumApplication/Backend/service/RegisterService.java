package com.museumApplication.Backend.service;



import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;


    public Users addNewUser(Users users)
    {
        return userRepository.save(users);
    }
}
