package com.bankTransaction.services;

import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.response.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
}
