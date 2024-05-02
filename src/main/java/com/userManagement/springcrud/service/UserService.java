package com.userManagement.springcrud.service;

import com.userManagement.springcrud.exception.DataNotFoundException;
import com.userManagement.springcrud.model.User;
import com.userManagement.springcrud.model.dto.UserDto;
import com.userManagement.springcrud.util.Response;
import org.apache.coyote.BadRequestException;


import java.util.List;

public interface UserService {
    User addUser(User user) throws DataNotFoundException, BadRequestException;

    List<User> getUsers() throws DataNotFoundException;

    User getUser(String appName) throws DataNotFoundException;

    void updateUser(Integer id, User user) throws DataNotFoundException, BadRequestException;

    void deleteUser(Integer id) throws DataNotFoundException, BadRequestException;

    User getUserById(Integer id) throws DataNotFoundException, BadRequestException;
}
