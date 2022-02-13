package com.ideaco.dia.controller;

import com.ideaco.dia.dto.UserDTO;
import com.ideaco.dia.model.UserModel;
import com.ideaco.dia.service.UserService;
import com.ideaco.dia.response.DataResponse;
import com.ideaco.dia.response.HandlerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    //access UserService class using annotation
    @Autowired
    private UserService userService;

    //define service get user by id
    @GetMapping("/user/{userId}")
    public UserModel getUser(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userService.findAllUsers();
    }

    //    create new user with request parameter & add response
    @PostMapping("/user")
    public UserModel createUser(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword,
                                @RequestParam("userEmail") String userEmail,
                                @RequestParam("userPhone") String userPhone,
                                @RequestParam("userName") String userAddress,
                                @RequestParam("userResume") String userResume) {

        UserModel newUser = userService.createUser(userName, userPassword, userEmail, userPhone, userAddress, userResume);

        if (newUser != null) {
            return newUser;
        } else {
            HandlerResponse.responseBadRequest(response, "400", "email already exist");
        }
        return new UserModel();
    }

//    add response to login validation
    @PostMapping("/user/login")
    public void loginAuth(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam("userEmail") String userEmail,
                                               @RequestParam("userPassword") String userPassword){
        UserDTO userLogin = userService.userLogin(userEmail, userPassword);

        if (userLogin != null) {
            DataResponse<UserDTO> dataResponse = new DataResponse<>();
            dataResponse.setData(userLogin);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        }else {
            HandlerResponse.responseBadRequest(response, "400", "email and password didn't match");
        }
    }
}