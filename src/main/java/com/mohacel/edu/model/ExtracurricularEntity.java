package com.mohacel.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ExtracurricularEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCurricularId;
    private String sports;
    private String club;
    private String otherActivities;
}
