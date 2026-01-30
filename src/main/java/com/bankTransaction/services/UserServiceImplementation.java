package com.bankTransaction.services;

import com.bankTransaction.data.model.User;
import com.bankTransaction.data.repositories.UserRepository;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.response.RegisterUserResponse;
import com.bankTransaction.exception.UserAlreadyExistException;
import com.bankTransaction.util.mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankTransaction.util.mapper.mapToRegisterUserResponse;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new UserAlreadyExistException("User already exists");});
        User user = mapper.mapToRegisterUser(request);
        User savedUser = userRepository.save(user);

        return mapToRegisterUserResponse(savedUser);
    }
}
