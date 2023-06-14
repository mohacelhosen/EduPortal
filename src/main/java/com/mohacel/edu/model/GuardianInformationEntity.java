package com.mohacel.edu.model;

import com.mohacel.edu.dto.GuardianAddress;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class GuardianInformationEntity {
    // legal guardian  information we need
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guardianInformationId;
    private String guardianFullName;
    private String relationshipToStudent;
    private String guardianEmailAddress;
    private String guardianContact;
    private GuardianAddress guardianAddress;
    private String authorizedPickup;
}
