package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Scanner;

@Data
@Entity
public class MedicalEmergencyContactEntity {
    // if they have any medical issue or under treatment for emergency how we will contact
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalContactId;
    @OneToOne(mappedBy = "medicalEmergencyContact")
    private CompleteUserEntity completeUser;
    private String fullName;
    private String relationship;
    private String contactNumber;
    private String hospitalName;
}
