package com.mohacel.edu.dto;

import lombok.Data;

@Data
public class MedicalEmergencyContact {
    // if they have any medical issue or under treatment for emergency how we will contact
    private String fullName;
    private String relationship;
    private String contactNumber;
    private String hospitalName;
}
