package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ExtracurricularEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCurricularId;

    @OneToOne(mappedBy = "extracurricular")
    private CompleteUserEntity completeUser;
    private String sports;
    private String club;
    private String otherActivities;
}
