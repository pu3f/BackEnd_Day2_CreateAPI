package com.ideaco.dia.service;

import com.ideaco.dia.dto.UserDTO;
import com.ideaco.dia.model.UserModel;
import com.ideaco.dia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //    get user by id
    public UserModel getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    //    get all users
    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    //    create user
    public UserModel createUser(String userName,
                                String userPassword,
                                String userEmail,
                                String userPhone,
                                String userAddress,
                                String userResume) {

//        register validate
        Optional<UserModel> newUserOpt = userRepository.findByUserEmail(userEmail);
        if (newUserOpt.isEmpty()) {
            return null;
        }

        UserModel newUser = new UserModel();
        newUser.setUserName(userName);
        newUser.setUserPassword(userPassword);
        newUser.setUserEmail(userEmail);
        newUser.setUserPhone(userPhone);
        newUser.setUserAddress(userAddress);
        newUser.setUserResume(userResume);

        return userRepository.save(newUser);
    }


//    login validation by user email & password
    public UserDTO userLogin(String userEmail, String userPassword){
        Optional<UserModel> userOpt = userRepository.findUserLogin(userEmail, userPassword);
        if (userOpt.isEmpty()) {
            return null;
        }
        return convertToDTO(userOpt.get());
    }

//    using UserDTO
    private UserDTO convertToDTO(UserModel userModel) {

//        new UserDTO with constructor
        return new UserDTO(userModel.getUserEmail(), userModel.getUserPassword());
    }

}
