package com.bankTransaction.util;

import com.bankTransaction.data.model.*;
import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.request.NinRegistrationRequest;
import com.bankTransaction.dto.request.RegisterUserRequest;
import com.bankTransaction.dto.response.BvnRegistrationResponse;
import com.bankTransaction.dto.response.DoTransferResponse;
import com.bankTransaction.dto.response.NinRegistrationResponse;
import com.bankTransaction.dto.response.RegisterUserResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bankTransaction.util.GenerateNin.uniqueNin;
import static com.bankTransaction.util.UniqueAccountNumber.uniqueAccountNumber;
import static com.bankTransaction.util.GenerateBvn.uniqueBvn;
import static com.bankTransaction.util.PasswordHarsh.hashPassword;

public class mapper {

    public static User mapToRegisterUser(RegisterUserRequest request){
        User newUser = new User();
        newUser.setFirstName(request.getFirstName().trim().toLowerCase());
        newUser.setLastName(request.getLastName().trim().toLowerCase());
        newUser.setAddress(request.getAddress().trim().toLowerCase());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setEmail(request.getEmail().trim().toLowerCase());
        newUser.setRole(Role.CUSTOMER);
        newUser.setAccountType(request.getAccountType());
        newUser.setPassword(hashPassword(request.getPassword()));
        newUser.setAccountNumber(uniqueAccountNumber());
        return newUser;
    }

    public static RegisterUserResponse mapToRegisterUserResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setFullName(user.getFirstName().trim().toLowerCase() + " " + user.getLastName().trim().toLowerCase());
        response.setEmail(user.getEmail().trim().toLowerCase());
        response.setAccountType(user.getAccountType());
        response.setAccountNumber(user.getAccountNumber());
        response.setMessage("User registered successfully");
        response.setDateOfRegistration(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm ss").format(LocalDateTime.now()));
        return response;
    }

    public static Bvn mapToRegisterBvn(BvnRegistrationRequest request){
        String bvn = uniqueBvn();
        Bvn record = new Bvn();
        record.setBvn(bvn);
        record.setFirstName(request.getFirstName().trim().toLowerCase());
        record.setLastName(request.getLastName().trim().toLowerCase());
        record.setDateOfBirth(request.getDateOfBirth());
        record.setPhoneNumber(request.getPhoneNumber());
        record.setEmail(request.getEmail().trim().toLowerCase());
        record.setAddress(request.getAddress().trim().toLowerCase());
        record.setGender(Gender.valueOf(request.getGender().name()));

        return record;
    }

    public static BvnRegistrationResponse mapToRegisterBvnResponse(Bvn record){
        BvnRegistrationResponse response = new BvnRegistrationResponse();
        response.setBvn(record.getBvn());
        response.setFirstName(record.getFirstName());
        response.setLastName(record.getLastName());
        response.setMessage("BVN generated successfully");
        return response;
    }

    public static Nin mapToRegisterNinRequest(NinRegistrationRequest request){
        String nin = uniqueNin();

        Nin record = new Nin();

        record.setNin(nin);
        record.setFirstName(request.getFirstName().trim().toLowerCase());
        record.setLastName(request.getLastName().trim().toLowerCase());
        record.setDateOfBirth(request.getDateOfBirth());
        record.setGender(Gender.valueOf(request.getGender().name()));

        return record;
    }

    public static NinRegistrationResponse mapToRegisterNinResponse(Nin record){
        NinRegistrationResponse response = new NinRegistrationResponse();
        response.setNin(record.getNin());
        response.setFirstName(record.getFirstName());
        response.setLastName(record.getLastName());
        response.setMessage("NIN generated successfully");

        return response;
    }

    public static DoTransferResponse mapToTransferResponse(){
        DoTransferResponse response = new DoTransferResponse();
        response.setMessage("Transfer successful");
        response.setDateOfTransaction(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm ss").format(LocalDateTime.now()));

        return response;
    }
}
