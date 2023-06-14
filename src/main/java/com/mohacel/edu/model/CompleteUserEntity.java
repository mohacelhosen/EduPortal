package com.mohacel.edu.model;

import com.mohacel.edu.dto.*;
import com.mohacel.edu.model.UserAddressEntity;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAddressId")
    private UserAddressEntity userAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userCurricularId")
    private ExtracurricularEntity extracurricular;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userEmergencyContactId")
    private EmergencyContactEntity emergencyContact;
    private GuardianInformation guardianInformation;
    private MedicalInformation medicalInformation;
    private MedicalEmergencyContact medicalEmergencyContact;
}
