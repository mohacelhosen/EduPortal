package com.mohacel.edu.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CompleteUserDto {
    // we need all the information regrading to admission
    private String userId;
    private String fullName;
    private String email;
    private LocalDate dob;
    private String gender;
    private Double height;
    private Double weight;
    private String userContactNumber;
    private String userNationality;
    private String academicInterests;
    private UserAddress userAddress;
    private Extracurricular extracurricular;
    private EmergencyContact emergencyContact;
    private GuardianInformation guardianInformation;
    private MedicalInformation medicalInformation;
    private MedicalEmergencyContact medicalEmergencyContact;
}
