package com.ideaco.dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //get user by id
    public UserModel getUserById(int userId){
        return userRepository.findById(userId).get();
    }

    //get all users
    public List<UserModel> findAllUsers(){
        return userRepository.findAll();
    }

    //create user
    public UserModel createUser(String userName,
                                String userPassword,
                                String userEmail,
                                String userPhone,
                                String userAddress,
                                String userResume){

        //register validate
        Optional<UserModel> userOpt = userRepository.findByUserEmail(userEmail);
        if (userOpt.isPresent()){
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

    //get user by email
    public UserModel getUserByEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail).get();
    }

    //login validation by user email & password
    public UserModel getUserByEmailAndPassword(String userEmail, String userPassword){
        Optional<UserModel> userOpt = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
        if (userOpt.isEmpty()){
            return null;
        }
        return userOpt.get();
    }

}
