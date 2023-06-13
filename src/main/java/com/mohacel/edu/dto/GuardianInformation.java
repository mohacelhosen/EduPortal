package com.mohacel.edu.dto;

import lombok.Data;

@Data
public class GuardianInformation {
    // legal guardian  information we need
    private String guardianFullName;
    private String relationshipToStudent;
    private String guardianEmailAddress;
    private String guardianContact;
    private GuardianAddress guardianAddress;
    private String authorizedPickup;
}
