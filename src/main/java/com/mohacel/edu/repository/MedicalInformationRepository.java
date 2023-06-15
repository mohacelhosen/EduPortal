package com.mohacel.edu.repository;

import com.mohacel.edu.model.MedicalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Integer> {
}
