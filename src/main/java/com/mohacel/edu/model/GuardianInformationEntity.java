package com.mohacel.edu.model;

import com.mohacel.edu.dto.GuardianAddress;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GuardianInformationEntity {
    // legal guardian  information we need
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guardianInformationId;
    @OneToOne(mappedBy = "guardianInformation")
    private CompleteUserEntity completeUser;
    private String guardianFullName;
    private String relationshipToStudent;
    private String guardianEmailAddress;
    private String guardianContact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guardianAddressId")
    private GuardianAddressEntity guardianAddress;
    private String authorizedPickup;
}
