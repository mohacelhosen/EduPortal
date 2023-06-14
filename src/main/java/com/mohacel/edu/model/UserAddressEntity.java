package com.mohacel.edu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class UserAddressEntity {
        @Id
        private String userAddressId;
        @OneToOne(mappedBy = "userAddress")
        private CompleteUserEntity completeUser;
        private String streetAddress;
        private String city;
        private String State;
        private String postalCode;
        private String country;
}
