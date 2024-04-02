package com.henryshain.userservice.service;

import com.henryshain.userservice.models.User;
import com.henryshain.userservice.repository.UserHSRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired
    private UserHSRepository userRepository ;

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public void createUser(){
//        UserHS userHS = UserHS.builder()
//                .userName(userRequest.getUserName())
//                .emailId(userRequest.getEmailId())
//                .password(userRequest.getPassword())
//                .roles(userRequest.getRoles())
//                .build();
        User userHS = new User();
        userHS.setName("Abcd");
        userRepository.save(userHS);
        LOGGER.info("User {} is saved ", userHS.getId());
    }
}
