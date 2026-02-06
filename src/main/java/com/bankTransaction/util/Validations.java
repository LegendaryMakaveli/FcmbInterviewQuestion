package com.bankTransaction.util;

import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.request.ChangePasswordRequest;
import com.bankTransaction.dto.request.NinRegistrationRequest;
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
        if(request.getTransactionPin().length() != 4) throw new InvalidExceptiion("Invalid transaction pin, Transaction pin must be 4 digits");
    }

    public static void validateChangePassword(ChangePasswordRequest request) {
        if(request.getNewPassword() == null || request.getNewPassword().trim().isEmpty())throw new InvalidExceptiion("New password cannot be empty");
        if(request.getOldPassword() == null || request.getOldPassword().trim().isEmpty())throw new InvalidExceptiion("Old password cannot be empty");
        if(request.getConfirmNewPassword() == null || !request.getNewPassword().equals(request.getConfirmNewPassword()) || request.getConfirmNewPassword().trim().isEmpty()) throw new InvalidExceptiion("New password and confirm password must be same");
    }

    public static void validateBvnRegistrationInput(BvnRegistrationRequest request) {
        String namePattern = "^[A-Za-z]{2,}$";
        String emailPattern = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        String phoneNumberPattern = "^(?:\\+234|234|0)[789][01]\\d{11}$";
        String dateOfBirthPattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";

        if(!request.getFirstName().matches(namePattern)) throw new InvalidExceptiion("Invalid first name");
        if(!request.getLastName().matches(namePattern)) throw new InvalidExceptiion("Invalid last name");
        if(!request.getEmail().matches(emailPattern)) throw new InvalidExceptiion("Invalid email");
        if(!request.getPhoneNumber().matches(phoneNumberPattern)) throw new InvalidExceptiion("Invalid phone number");
        if(!request.getDateOfBirth().matches(dateOfBirthPattern)) throw new InvalidExceptiion("Invalid date of birth");
    }

    public static void validateNinRegistrationInput(NinRegistrationRequest request) {
        String namePattern = "^[A-Za-z]{2,}$";
        String emailPattern = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        String dateOfBirthPath = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";

        if(!request.getFirstName().matches(namePattern)) throw new InvalidExceptiion("Invalid first name");
        if(!request.getLastName().matches(namePattern)) throw new InvalidExceptiion("Invalid last name");
        if(!request.getEmail().matches(emailPattern)) throw new InvalidExceptiion("Invalid email");
        if(!request.getDateOfBirth().matches(dateOfBirthPath)) throw new InvalidExceptiion("Invalid date of birth");
    }

}
