package com.mohacel.edu.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
