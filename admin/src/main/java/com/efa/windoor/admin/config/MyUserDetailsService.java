package com.efa.windoor.admin.config;

// Created by Shorasul Sh. on 14.08.2020

import com.efa.windoor.core.entities.UserEntity;
import com.efa.windoor.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username).orElse(null);
        if (userEntity != null)
            return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
        return null;
    }

    public UserEntity getId(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username).orElse(null);
    }

}
