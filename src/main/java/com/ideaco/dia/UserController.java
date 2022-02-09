package com.ideaco.dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    //access UserService class using annotation
    @Autowired
    private UserService userService;

    //define service get user by id
    @GetMapping("/user/{userId}")
    public UserModel getUser(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    //define service get user by email
    @GetMapping("/user/email/{userEmail}")
    public UserModel getUserByEmail(@PathVariable("userEmail") String userEmail){
        return userService.getUserByEmail(userEmail);
    }

    //define service get all users
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.findAllUsers();
    }

    //define service create new user
    @PostMapping("/user")
    public UserModel createUser(@RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword,
                                @RequestParam("userEmail") String userEmail,
                                @RequestParam("userPhone") String userPhone,
                                @RequestParam("userName") String userAddress,
                                @RequestParam("userResume") String userResume){

        return userService.createUser(userName, userPassword, userEmail, userPhone, userAddress, userResume );
    }


    //define service login validation
    @GetMapping("/user/login")
    public UserModel getUserByEmailAndPassword(@RequestParam("userEmail") String userEmail,
                                               @RequestParam("userPassword") String userPassword){
        UserModel userByEmail = userService.getUserByEmailAndPassword(userEmail, userPassword);
        if (userByEmail != null) {
            return userByEmail;
        }else {
            return new UserModel();
        }
    }
}