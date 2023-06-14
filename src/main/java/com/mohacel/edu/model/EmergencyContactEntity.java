package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EmergencyContactEntity {
    // immediate emergency contact
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userEmergencyContactId;
    @OneToOne(mappedBy = "emergencyContact")
    private CompleteUserEntity completeUser;
    private String fullName;
    private String relationship;
    private String contactNumber;
}
