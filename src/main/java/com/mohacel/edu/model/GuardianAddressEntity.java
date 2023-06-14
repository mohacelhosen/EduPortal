package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GuardianAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guardianAddressId;
    @OneToOne(mappedBy = "guardianAddress")
    private GuardianInformationEntity guardianInformation;
    private String streetAddress;
    private String city;
    private String State;
    private String postalCode;
    private String country;
}

