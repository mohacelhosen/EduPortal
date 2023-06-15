package com.mohacel.edu.repository;

import com.mohacel.edu.model.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Integer> {
}
