package com.mohacel.edu.dto;

import lombok.Data;

@Data
public class MedicalInformation {
    private String bloodGroup;
    private String PreviousSurgeries;
    private boolean asthma;
    private boolean diabetes;
    private String epilepsy;
    private String allergies;
    private String heartConditions;

}
