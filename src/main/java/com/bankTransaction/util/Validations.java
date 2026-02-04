package com.bankTransaction.util;

import com.bankTransaction.dto.request.ChangePasswordRequest;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.exception.InvalidExceptiion;

public class Validations {

    public static void validateUserSignUp(RegisterUserRequest request) {
        String namePattern = "^[A-Za-z]{2,}$";
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$";
        String emailPattern = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        if(!request.getFirstName().matches(namePattern) || !request.getLastName().matches(namePattern)) throw new InvalidExceptiion("Invalid name");
        if(!request.getPassword().matches(passwordPattern)) throw new InvalidExceptiion("Invalid password");
        if(!request.getEmail().matches(emailPattern)) throw new InvalidExceptiion("Invalid email");
        if(!request.getAccountType().equals("BUSINESS") || !request.getAccountType().equals("RETAIL") || !request.getAccountType().equals("STUDENT")) throw new InvalidExceptiion("Invalid account type");
    }

    public static void validateChangePassword(ChangePasswordRequest request) {
        if(request.getNewPassword() == null || request.getNewPassword().trim().isEmpty())throw new InvalidExceptiion("New password cannot be empty");
        if(request.getOldPassword() == null || request.getOldPassword().trim().isEmpty())throw new InvalidExceptiion("Old password cannot be empty");
        if(request.getEmail() == null || request.getEmail().trim().isEmpty())throw new InvalidExceptiion("Email cannot be empty");
        if(request.getConfirmNewPassword() == null || !request.getNewPassword().equals(request.getConfirmNewPassword()) || request.getConfirmNewPassword().trim().isEmpty()) throw new InvalidExceptiion("New password and confirm password must be same");
    }
}
