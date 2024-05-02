package com.userManagement.springcrud.controller;


import com.userManagement.springcrud.exception.DataNotFoundException;
import com.userManagement.springcrud.model.User;
import com.userManagement.springcrud.model.dto.UserDto;
import com.userManagement.springcrud.service.UserService;
import com.userManagement.springcrud.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class UserController extends Response{


    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    @Operation(summary = "Add Application user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Input Values",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public ResponseEntity<Response> addUser(@RequestBody User user) throws DataNotFoundException, BadRequestException {


        User savedUser = userService.addUser(user);

        return ResponseEntity.ok(response(savedUser, "Successfully added user"));
    }



    @GetMapping("/getUser")
    @Operation(summary = "Get all users list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public List<User> getUsers() throws DataNotFoundException{

        return userService.getUsers();
    }



    @GetMapping("/getByAppName")
    @Operation(summary = "Get user details by Application name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})

    public User getUser(@RequestParam String appName) throws DataNotFoundException{

        return  userService.getUser(appName);
    }


    @GetMapping("/getUserById/{id}")
    @Operation(summary = "Get user details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})

    public User getUserById(@PathVariable Integer id) throws DataNotFoundException, BadRequestException{

        return  userService.getUserById(id);
    }




    @PutMapping("/updateUser/{id}")
    @Operation(summary = "Update user by User ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Response updateUser(@PathVariable Integer id , @RequestBody User user) throws DataNotFoundException, BadRequestException {

        userService.updateUser(id, user);

        return response("User Updated successfully");
    }



    @DeleteMapping("/deleteUser/{id}")
    @Operation(summary = "Delete user By user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Response deleteUser(@PathVariable Integer id) throws DataNotFoundException, BadRequestException {

        userService.deleteUser(id);

        return response("User Deleted successfully");
    }

}
