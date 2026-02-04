package com.bankTransaction.dto.request;


import lombok.Data;

@Data
public class FindByUserDataRequest {
    String firstName;
    String lastName;
    String dateOfBirth;
}
