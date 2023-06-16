package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserAddressEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userAddressId;
        @OneToOne(mappedBy = "userAddress")
        private CompleteUserEntity completeUser;
        private String streetAddress;
        private String city;
        private String state;
        private String postalCode;
        private String country;
}
