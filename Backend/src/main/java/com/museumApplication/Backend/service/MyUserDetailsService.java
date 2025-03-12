package com.museumApplication.Backend.service;

import com.museumApplication.Backend.model.UserPrincipal;
import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByName(username);
        if(user==null)
        {
            System.out.println("User Not Found..");
            throw new UsernameNotFoundException("User Not Found..");
        }
        return new UserPrincipal(user);
    }
}
