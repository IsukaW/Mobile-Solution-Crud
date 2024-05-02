package com.userManagement.springcrud.service.impl;

import com.userManagement.springcrud.exception.DataNotFoundException;
import com.userManagement.springcrud.model.User;
import com.userManagement.springcrud.model.dto.UserDto;
import com.userManagement.springcrud.repository.UserRepository;
import com.userManagement.springcrud.service.UserService;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) throws DataNotFoundException, BadRequestException {
        logger.info("UserServiceImpl -> Adding User -> started!");

        // Check for duplicate user based on unique identifier
        if (userRepository.existsByappKey(user.getAppKey())) {
            throw new BadRequestException("User already exists with Apllication Key: " + user.getAppKey());
        } else if (userRepository.existsByappName(user.getAppName())) {
            throw new BadRequestException("User already exists with Application Name: " + user.getAppName());
        }

        User user1 = userRepository.save(user);

        return user1;
    }

    @Override
    public List<User> getUsers() throws DataNotFoundException {

        logger.info("UserServiceImpl -> Get all User -> started!");

        List<User> user1 = userRepository.findAll();

        return user1;
    }

    @Override
    public User getUser(String appName) throws DataNotFoundException {

        logger.info("UserServiceImpl -> Get User By Application Name -> started!");

         User user = userRepository.findByAppName(appName);
         if (user != null) {
             return user;
         } else {

             return null;
         }

    }

    @Override
    public void updateUser(Integer id, User user) throws DataNotFoundException, BadRequestException {

        logger.info("UserServiceImpl -> Update User By ID -> started!");
        userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User id " + id));

        user.setId(id);

//        if (userRepository.existsByname(user.getName())) {
//            throw new BadRequestException("User already exists with User Name: " + user.getName());
//        }

         userRepository.save(user);

        logger.info("UserServiceImpl -> Update User By ID -> Ended!");
    }

    @Override
    public void deleteUser(Integer id) throws DataNotFoundException, BadRequestException {

        logger.info("UserServiceImpl -> Delete User By ID -> started!");
        //check user in the database
        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User id " + id));

        userRepository.delete(user);
    }

    @Override
    public User getUserById(Integer id) throws DataNotFoundException, BadRequestException {

        logger.info("UserServiceImpl -> Delete User By ID -> started!");

        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User id " + id));

        return user;
    }
}
