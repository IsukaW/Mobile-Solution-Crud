package com.userManagement.springcrud.controller;

import com.userManagement.springcrud.exception.DataNotFoundException;
import com.userManagement.springcrud.model.User;
import com.userManagement.springcrud.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Logger;

@Controller
public class WebController {

    Logger logger = Logger.getLogger("info");

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "SaveUser";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUserPost(@ModelAttribute("user") User user) throws DataNotFoundException, BadRequestException {
        logger.info(user.toString());
        userService.addUser(user);
        return "redirect:/addUser";
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUserDetails(Model model) throws DataNotFoundException {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "GetUser";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) throws DataNotFoundException , BadRequestException{

        logger.info(String.valueOf(id));
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User witrh Id" + id + "Has been deleted successfully");
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestParam("name") String name) throws DataNotFoundException, BadRequestException {
        logger.info(name);
        logger.info(String.valueOf(id));

        User newUser = userService.getUserById(id);
        newUser.setName(name);
        userService.updateUser(id, newUser);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }


}
