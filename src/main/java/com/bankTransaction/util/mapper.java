package com.bankTransaction.util;

import com.bankTransaction.data.model.User;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.response.RegisterUserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class mapper {

    public static User mapToRegisterUser(RegisterUserRequest request){
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setAccountType(request.getAccountType());
        newUser.setPassword(request.getPassword());
        newUser.setAccountNumber(request.getAccountNumber());
        return newUser;
    }

    public static RegisterUserResponse mapToRegisterUserResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setAccountType(user.getAccountType());
        response.setAccountNumber(user.getAccountNumber());
        response.setMessage("User registered successfully");
        response.setDateOfRegistration(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm ss").format(LocalDateTime.now()));
        return response;
    }
}
