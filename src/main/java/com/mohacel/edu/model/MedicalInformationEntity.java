package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MedicalInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalInformationId;
    private String bloodGroup;
    private String PreviousSurgeries;
    private boolean asthma;
    private boolean diabetes;
    private String epilepsy;
    private String allergies;
    private String heartConditions;

}