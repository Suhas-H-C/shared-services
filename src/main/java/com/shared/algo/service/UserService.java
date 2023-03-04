package com.shared.algo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public final class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Logic to get the user form the Database
        return new User("suhas", "$2a$10$wdrTYak5lHz9uf9UrrY3WO7KXf9b.NUThyY1Bv3aoawyj7fb59VXm", new ArrayList<>());
    }
}
