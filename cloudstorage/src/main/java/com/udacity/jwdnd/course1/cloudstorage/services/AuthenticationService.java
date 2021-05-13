package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;
    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        System.out.println("Inside authentication service");
        User user=userMapper.getUserDetails(username);
        if(user!=null)
        {
            String encodedSalt=user.getSalt();
            String hashedPassword=hashService.getHashedValue(password,encodedSalt);
            System.out.println("user entered password is "+ hashedPassword);
            System.out.println("password in db "+user.getPassword());
            if(user.getPassword().equals(hashedPassword)){
                System.out.println("password matched");
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

            }
        }
        System.out.println("passwords didnt match");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
