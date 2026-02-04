package com.bankTransaction.services;

import com.bankTransaction.dto.request.LoginUserRequest;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.request.ChangePasswordRequest;
import com.bankTransaction.dto.request.ResetPasswordRequest;
import com.bankTransaction.dto.response.LoginUserResponse;
import com.bankTransaction.dto.response.RegisterUserResponse;

public interface AuthService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
    LoginUserResponse login(LoginUserRequest request);
    String changePassword(ChangePasswordRequest request);
    String resetPassword(ResetPasswordRequest newPassword);
}
