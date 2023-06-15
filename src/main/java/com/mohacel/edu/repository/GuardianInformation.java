package com.mohacel.edu.repository;

import com.mohacel.edu.model.GuardianInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianInformation extends JpaRepository<GuardianInformationEntity, Integer> {
}
