package com.mohacel.edu.dto;

import lombok.Data;

@Data
public class AuthorizedPickup {
    // who will pick up your child
    private String fullName;
    private String gender;
    private Integer age;
    private String contactNumber;
    private String NID_number;
}
