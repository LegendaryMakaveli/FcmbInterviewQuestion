package com.bankTransaction.dto.request;

import com.bankTransaction.data.model.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class BvnRegistrationRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Gender is required")
    private Gender gender;
}
