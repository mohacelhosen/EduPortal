package com.mohacel.edu.dto;

import lombok.Data;

@Data
public class GuardianAddress {
    private String streetAddress;
    private String city;
    private String State;
    private String postalCode;
    private String country;
}
