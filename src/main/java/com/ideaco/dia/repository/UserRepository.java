package com.ideaco.dia.repository;

import com.ideaco.dia.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUserEmail(String userEmail);

    Optional<UserModel> findUserLogin(String userEmail, String userPassword);

}
