package com.bankTransaction.dto.request;

import com.bankTransaction.data.model.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class NinRegistrationRequest {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Gender is required")
    private Gender gender;
}
