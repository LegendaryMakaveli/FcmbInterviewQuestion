package com.bankTransaction.data.model;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class BvnData {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String dateOfBirth;
}
