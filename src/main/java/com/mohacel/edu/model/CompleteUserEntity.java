package com.mohacel.edu.model;

import com.mohacel.edu.dto.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class CompleteUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String fullName;
    private String email;
    private String password;
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
