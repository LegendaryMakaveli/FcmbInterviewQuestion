package com.bankTransaction.services;

import com.bankTransaction.data.model.User;
import com.bankTransaction.data.repositories.UserRepository;
import com.bankTransaction.dto.request.LoginUserRequest;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.request.ChangePasswordRequest;
import com.bankTransaction.dto.request.ResetPasswordRequest;
import com.bankTransaction.dto.response.LoginUserResponse;
import com.bankTransaction.dto.response.RegisterUserResponse;
import com.bankTransaction.exception.InvalidAccountException;
import com.bankTransaction.exception.InvalidExceptiion;
import com.bankTransaction.exception.UserAlreadyExistException;
import com.bankTransaction.exception.UserNotFoundException;
import com.bankTransaction.security.JwtService;
import com.bankTransaction.util.mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankTransaction.util.Validations.validateChangePassword;
import static com.bankTransaction.util.Validations.validateUserSignUp;
import static com.bankTransaction.util.mapper.mapToRegisterUserResponse;
import com.bankTransaction.util.PasswordHarsh;

@AllArgsConstructor
@Service
public class AuthServiceImplementation implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        validateUserSignUp(request);
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new UserAlreadyExistException("User already exists");});
        User user = mapper.mapToRegisterUser(request);
        User savedUser = userRepository.save(user);

        return mapToRegisterUserResponse(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        if(request.getEmail() == null || request.getEmail().trim().isEmpty())throw new InvalidExceptiion("Invalid credentials");
        if(request.getPassword() == null || request.getPassword().trim().isEmpty())throw new InvalidExceptiion("Invalid credentials");
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidAccountException("Invalid credentials"));
        if(!PasswordHarsh.checkPassword(request.getPassword(), user.getPassword())) throw new InvalidAccountException("Invalid credentials");

        String loginToken = jwtService.generateToken(user);

        return LoginUserResponse.builder()
               .token(loginToken)
               .message("Login successful")
               .role(user.getRole().name())
               .userId(user.getId())
               .email(user.getEmail())
               .build();
    }

    @Override
    public String changePassword(ChangePasswordRequest request) {
        validateChangePassword(request);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User account not found"));
        if(!PasswordHarsh.checkPassword(request.getOldPassword(), user.getPassword())) throw new InvalidExceptiion("Old password is not correct");
        if(PasswordHarsh.checkPassword(request.getNewPassword(), user.getPassword())) throw new InvalidExceptiion("New password must be different from old password");

        user.setPassword(PasswordHarsh.hashPassword(request.getNewPassword()));
        userRepository.save(user);

        return "Password change successfully";
    }

    @Override
    public String resetPassword(ResetPasswordRequest newPassword) {
        if(newPassword.getNewPassword() == null || newPassword.getNewPassword().trim().isEmpty())throw new InvalidExceptiion("Invalid credentials");
        if(newPassword.getEmail() == null || newPassword.getEmail().trim().isEmpty())throw new InvalidExceptiion("Invalid credentials");
        User user = userRepository.findByEmail(newPassword.getEmail()).orElseThrow(() -> new UserNotFoundException("User account not found"));
        user.setPassword(PasswordHarsh.hashPassword(newPassword.getNewPassword()));
        userRepository.save(user);
        return "Password reset successfully";
    }


}
